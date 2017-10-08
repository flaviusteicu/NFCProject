package com.example.flavius.nfcproject;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.provider.Settings;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Flavius on 08-Oct-17.
 */

public class NFCLogic
{

    public static String ACCESS_FOR = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";
    public static final String FIELDS_DELIMITER = "%%";
    public static final String LINE_DELIMITER = "&&";

    public static void write(String text, Tag tag) throws IOException, FormatException {
        NdefRecord[] recordsToMessage = new NdefRecord[2];
        ArrayList<NdefRecord> records = new ArrayList<NdefRecord>();
        records.add(createRecord(text));
        records.add(NdefRecord.createApplicationRecord("1"));


        NdefMessage message = new NdefMessage(records.toArray(recordsToMessage));
        // Get an instance of Ndef for the tag.
        Ndef ndef = Ndef.get(tag);
        // Enable I/O
        ndef.connect();
        // Write the message
        ndef.writeNdefMessage(message);
        // Close the connection
        ndef.close();
    }

    public static NdefRecord createRecord(String text) throws UnsupportedEncodingException {
        String lang = "en";
        byte[] textBytes = text.getBytes();
        byte[] langBytes = lang.getBytes("US-ASCII");
        int langLength = langBytes.length;
        int textLength = textBytes.length;
        byte[] payload = new byte[1 + langLength + textLength];

        // set status byte (see NDEF spec for actual bits)
        payload[0] = (byte) langLength;

        // copy langbytes and textbytes into payload
        System.arraycopy(langBytes, 0, payload, 1, langLength);
        System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);

//        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_EXTERNAL_TYPE, "com.example.flavius6.nfcprofiles".getBytes(), new byte[0], payload);
        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], payload);
//        NdefRecord extRecord = new NdefRecord(
//                NdefRecord.TNF_EXTERNAL_TYPE, "com.example:externalType", new byte[0], payload);

//
//        NdefRecord Myrecord = createMime(
//                        "com.example.flavius6.nfcprofiles", text.getBytes());


//        String domain = "com.example.flavius6.nfcprofiles"; //usually your app's package name
//        String type = "externalType";
//        NdefRecord extRecord = NdefRecord.createExternal(domain, type, payload);
        return recordNFC;
    }

    public static String buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return "";

        String text = "";
//        String tagId = new String(msgs[0].getRecords()[0].getType());
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"
        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");

        try {
            // Get the Text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }
        Log.i("NFC", "textul e : " + text);
        return text;
    }


    public static Context context;
}



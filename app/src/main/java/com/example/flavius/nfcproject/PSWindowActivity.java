package com.example.flavius.nfcproject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PSWindowActivity extends AppCompatActivity
{
    @BindView(R.id.switch1)
            Switch switchNFC;

    @BindView(R.id.AccessID)
            TextView textAccess;

    @BindView(R.id.UsernameID)
            TextView textUsername;

    @BindView(R.id.PasswordID)
            TextView textPassword;


    NfcAdapter mNfcAdapter;
    public static final String MIME_TEXT_PLAIN = "text/plain";

    private List<String> configToBeWrite = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pswindow);
        ButterKnife.bind(this);
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Access granted", Snackbar.LENGTH_SHORT).show();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null)
        {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setupForegroundDispatch(this, mNfcAdapter);
    }

    public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter)
    {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try
        {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (IntentFilter.MalformedMimeTypeException e)
        {
            throw new RuntimeException("Check your mime type.");
        }

        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {


            if (switchNFC.isChecked())
            {
                //write to a tag nfc
                Tag nfcTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                try {
/*
                    String textToWrite = textAccess.getText().toString();
*/
                    String textToWrite = textAccess.getText() + NFCLogic.LINE_DELIMITER + textUsername.getText()
                            + NFCLogic.LINE_DELIMITER + textPassword.getText().toString();
                    NFCLogic.write(textToWrite, nfcTag);
                    Snackbar.make(switchNFC, "Scris", Snackbar.LENGTH_LONG);
                } catch (IOException e) {
                    e.printStackTrace();
                    Snackbar.make(switchNFC, "Eroare", Snackbar.LENGTH_LONG);

                } catch (FormatException e) {
                    e.printStackTrace();
                    Snackbar.make(switchNFC, "Eroare", Snackbar.LENGTH_LONG);

                }
            }
            else
                {
                ///read from a tag
                Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                NdefMessage[] msgs = null;
                if (rawMsgs != null) {
                    msgs = new NdefMessage[rawMsgs.length];
                    for (int i = 0; i < rawMsgs.length; i++) {
                        msgs[i] = (NdefMessage) rawMsgs[i];
                        Toast.makeText(getApplicationContext(), "mesaj from nfc: " + msgs[i], Toast.LENGTH_LONG);

                    }
                    String textFromNfc = NFCLogic.buildTagViews(msgs);
                    ReadingTheData(textFromNfc);
                }
            }
        }
    }


    private void ReadingTheData(String text)
    {
        String []  parti = text.split(NFCLogic.LINE_DELIMITER);
        for(int i=0;i<=2;i++)
        {
            textAccess.setText(parti[0]);
            textUsername.setText(parti[1]);
            textPassword.setText(parti[2]);
        }
    }

    //LIstView/


   /* public void SnackbarSuccess(View view)
    {
        Snackbar snackbar = Snackbar.make(view, "Credentials added", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }*/

}

package com.example.flavius.nfcproject;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GoToPasswordsPage (View view)
    {
        Intent accessPasswordsActivity = new Intent(getApplicationContext(), pswindow.class);
        startActivity(accessPasswordsActivity);
    }
}

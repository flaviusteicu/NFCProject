package com.example.flavius.nfcproject;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class pswindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pswindow);
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Access granted", Snackbar.LENGTH_SHORT).show();
    }

    public void SnackbarSuccess(View view)
    {
        Snackbar snackbar = Snackbar.make(view, "Credentials added", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}

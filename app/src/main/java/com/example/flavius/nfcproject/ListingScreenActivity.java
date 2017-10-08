package com.example.flavius.nfcproject;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_screen);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.floatingActionButtonID)
            public void clickingButton (View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        UnFragment unFragment = new UnFragment();
        unFragment.show(fm, "What");
    }
}

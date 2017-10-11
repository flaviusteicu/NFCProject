package com.example.flavius.nfcproject;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListingScreenActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_screen);

        ButterKnife.bind(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewID);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<CardViewData> myList = new ArrayList<CardViewData>();
        mAdapter = new MyAdapter(myList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.floatingAddButtonID)
    public void clickingButton (View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        UnFragment unFragment = new UnFragment();
        unFragment.show(fm, "What");
    }
}

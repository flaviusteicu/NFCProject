package com.example.flavius.nfcproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flavius on 11-Oct-17.
 */

public class CardViewData {
    int accessfor;
    int username;
    int password;

    CardViewData(int accessfor, int username, int password) {
        this.accessfor = accessfor;
        this.username = username;
        this.password = password;
    }


    private List<CardViewData> cardsData;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        cardsData = new ArrayList<>();
        cardsData.add(new CardViewData(R.id.AccessID, R.id.UsernameID, R.id.PasswordID));}
}

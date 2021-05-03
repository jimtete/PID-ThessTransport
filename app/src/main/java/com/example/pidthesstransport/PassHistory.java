package com.example.pidthesstransport;

import java.util.ArrayList;

public class PassHistory {


    private ArrayList<PassPurchases> purchasedPassesArray = new ArrayList<PassPurchases>();

    public PassHistory(){}

    public ArrayList<PassPurchases> getPurchasedPassesArray() {
        return purchasedPassesArray;
    }

    public void setPurchasedPassesArray(ArrayList<PassPurchases> purchasedPassesArray) {
        this.purchasedPassesArray = purchasedPassesArray;
    }

    public void AddPurchase(PassPurchases PP){

        if (this.purchasedPassesArray==null)purchasedPassesArray = new ArrayList<PassPurchases>();

        this.purchasedPassesArray.add(PP);

    }

    public PassPurchases ShowPasses(){

        return null;
    }
}

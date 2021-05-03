package com.example.pidthesstransport;

import java.util.Date;

public class PassPurchases {

    private String passIdentificator;

    private Date timePurchased;

    private Pass pass;

    public PassPurchases(String passIdentificator, Pass pass) {
        this.passIdentificator = passIdentificator;
        this.timePurchased = new Date();
        this.pass = pass;
    }

    public boolean isActive(){
        int amountOfDays = 0;
        if (pass.getType()==0)amountOfDays=30;
        if (pass.getType()==1)amountOfDays=90;
        if (pass.getType()==2)amountOfDays=180;

        return true;


    }

    public PassPurchases(){}

    public String getPassIdentificator() {
        return passIdentificator;
    }

    public void setPassIdentificator(String passIdentificator) {
        this.passIdentificator = passIdentificator;
    }

    public Date getTimePurchased() {
        return timePurchased;
    }

    public void setTimePurchased(Date timePurchased) {
        this.timePurchased = timePurchased;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }
}

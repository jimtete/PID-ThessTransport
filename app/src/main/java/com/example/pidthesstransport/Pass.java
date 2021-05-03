package com.example.pidthesstransport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pass {

    @SerializedName("type")
    @Expose
    private int type; //0 for 30days, 1 for 90 days, 2 for 180days


    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("discount")
    @Expose
    private boolean discount;

    @SerializedName("description")
    @Expose
    private String description;

    public Pass(){}

    public Pass(int type, double price, boolean discount, String description) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


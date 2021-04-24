package com.example.pidthesstransport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tickets {

    @SerializedName("type")
    @Expose
    private int type;

    @SerializedName("ticketdurationinmin")
    @Expose
    private int ticketDurationInMin;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("discount")
    @Expose
    private boolean discount;

    @SerializedName("description")
    @Expose
    private String description;

    public Tickets(int type, int ticketDurationInMin, double price, boolean discount, String description) {
        this.type = type;
        this.ticketDurationInMin = ticketDurationInMin;
        this.price = price;
        this.discount = discount;
        this.description = description;
    }

    public double CalculateCost(){
        double base = 1.20;
        if (type==1) base-=0.20;

        if (discount)return base/2;
        else return base;
    }

    public Tickets(){}

    public int getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getTicketDurationInMin() {
        return ticketDurationInMin;
    }

    public void setTicketDurationInMin(int ticketDurationInMin) {
        this.ticketDurationInMin = ticketDurationInMin;
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

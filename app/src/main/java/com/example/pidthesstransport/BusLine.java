package com.example.pidthesstransport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusLine {

    @SerializedName("number")
    @Expose
    private int number;

    @SerializedName("description")
    @Expose
    private String description;

    public BusLine(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public BusLine(){}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "number=" + number +
                ", description='" + description + '\'' +
                '}';
    }
}

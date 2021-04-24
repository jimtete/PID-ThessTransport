package com.example.pidthesstransport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bus {

    @SerializedName("busreg")
    @Expose
    private String busReg;

    @SerializedName("busline")
    @Expose
    private BusLine busLine;

    public Bus(String busReg, BusLine busLine) {
        this.busReg = busReg;
        this.busLine = busLine;
    }

    public Bus(){}

    public String getBusReg() {
        return busReg;
    }

    public void setBusReg(String busReg) {
        this.busReg = busReg;
    }

    public BusLine getBusLine() {
        return busLine;
    }

    public void setBusLine(BusLine busLine) {
        this.busLine = busLine;
    }

    public String SerializeBusInfo(){
        String serializable="";
        serializable+=this.busReg+":"+this.busLine.getNumber()+":"+this.busLine.getDescription();

        return serializable;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busReg='" + busReg + '\'' +
                ", busLine=" + busLine.toString() +
                '}';
    }
}

package com.example.pidthesstransport;

public class Balance {

    private Double balance;
    private int points;

    public Balance(){};

    public Balance(Double balance, int points) {
        this.balance = balance;
        this.points = points;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

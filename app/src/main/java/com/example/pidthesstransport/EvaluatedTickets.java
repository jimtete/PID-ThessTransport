package com.example.pidthesstransport;
import java.util.Date;


public class EvaluatedTickets {

    private Date validationTime;
    private Tickets ticket;
    private Bus bus;

    public EvaluatedTickets(Tickets ticket, Bus bus) {
        this.validationTime = new Date();
        this.ticket = ticket;
        this.bus = bus;
    }

    public EvaluatedTickets(){}

    public Date getValidationTime() {
        return validationTime;
    }

    public void setValidationTime(Date validationTime) {
        this.validationTime = validationTime;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @Override
    public String toString() {
        return "EvaluatedTickets{" +
                "validationTime=" + validationTime +
                ", ticket=" + ticket +
                ", bus=" + bus +
                '}';
    }
}

package com.example.pidthesstransport;

import java.util.ArrayList;

public class TicketHistory {

    private ArrayList<EvaluatedTickets> evaluatedTicketsArray;

    public TicketHistory(ArrayList<EvaluatedTickets> evaluatedTicketsArray) {
        this.evaluatedTicketsArray = new ArrayList<EvaluatedTickets>();
    }

    public TicketHistory(){}

    public ArrayList<EvaluatedTickets> getEvaluatedTicketsArray() {
        return evaluatedTicketsArray;
    }

    public void setEvaluatedTicketsArray(ArrayList<EvaluatedTickets> evaluatedTicketsArray) {
        this.evaluatedTicketsArray = evaluatedTicketsArray;
    }

    public EvaluatedTickets ShowTickets(){

        return null;
    }


}

package com.example.pidthesstransport;

import java.util.ArrayList;

public class TicketHistory {

    private ArrayList<EvaluatedTickets> evaluatedTicketsArray= new ArrayList<EvaluatedTickets>();

    public TicketHistory(){}

    public ArrayList<EvaluatedTickets> getEvaluatedTicketsArray() {
        return evaluatedTicketsArray;
    }

    public void setEvaluatedTicketsArray(ArrayList<EvaluatedTickets> evaluatedTicketsArray) {
        this.evaluatedTicketsArray = evaluatedTicketsArray;
    }

    public void AddEvaluation(EvaluatedTickets ET){
        if (this.evaluatedTicketsArray==null)evaluatedTicketsArray= new ArrayList<EvaluatedTickets>();

        this.evaluatedTicketsArray.add(ET);
    }

    public EvaluatedTickets ShowTickets(){

        return null;
    }


}

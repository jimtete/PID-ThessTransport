package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;


public class TicketPurchaseConfirmationFragment extends Fragment {

    public static FirebaseFirestore dataBase;
    public static FragmentManager fragmentManager;
    public User logedInUser;
    public Tickets selectedTicket;
    String hashedEmail;
    String amount;
    String bus;

    ImageButton imageButton;


    View view;


    public TicketPurchaseConfirmationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBase = FirebaseFirestore.getInstance();
        view = inflater.inflate(R.layout.fragment_ticket_purchase_confirmation, container, false);
        hashedEmail = getArguments().getString("Email");
        amount = getArguments().getString("Ticket");
        bus = getArguments().getString("Line");



        DocumentReference reference = dataBase.collection("User").document(hashedEmail);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SetLogedInUser(documentSnapshot.toObject(User.class));
            }
        });

        imageButton = view.findViewById(R.id.imageButton_continue);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, new HomeFragment());
                transaction.commit();
            }
        });



        return view;
    }

    private void UpdateUserBalance(String amount) {

        DocumentReference reference = dataBase.collection("Tickets").document(amount);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DoPurchase(documentSnapshot.toObject(Tickets.class));
            }
        });

    }

    private void DoPurchase(Tickets ticket) {

        double sendAmount = ticket.CalculateCost();

        if (logedInUser.getBalance().getBalance()<sendAmount){
            ErrorOnPurchase(-1);
            return;
        }

        DocumentReference reference = dataBase.collection("Buses").document(bus);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                Bus selectedBus = documentSnapshot.toObject(Bus.class);
                EvaluatedTickets tempEvaluatedTickets = new EvaluatedTickets(ticket,selectedBus);



                TicketHistory tempTicketHistory = logedInUser.getTicketHistory();

                tempTicketHistory.AddEvaluation(tempEvaluatedTickets);



                logedInUser.ticketPurchase(sendAmount);

                logedInUser.setTicketHistory(tempTicketHistory);


                dataBase.collection("User").document(hashedEmail)
                        .set(logedInUser, SetOptions.merge());

                OasthHomeActivity activity = (OasthHomeActivity)getActivity();
                activity.logedInUser = logedInUser;

                ErrorOnPurchase(-2);
            }
        });




    }

    private void ErrorOnPurchase(int i) {


        TextView error = view.findViewById(R.id.textView_confirmation);
        switch (i){
            case -1:
                error.setText("Δεν έχετε αρκετό υπόλοιπο για να πραγματοποιηθεί η αγορά");
                break;
            case -2:
                error.setText("Το εισιτήριο αγοράσθηκε με επιτυχία!");
                break;
        }

    }


    private void SetLogedInUser(User user) {

        logedInUser = user;
        UpdateUserBalance(amount);

    }
}
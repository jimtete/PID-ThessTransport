package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


public class BalanceFragment extends Fragment {


    public static FirebaseFirestore dataBase;
    public User logedInUser;
    String hashedEmail;
    public BalanceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);

        hashedEmail = getArguments().getString("email");


        dataBase = FirebaseFirestore.getInstance();
        DocumentReference reference = dataBase.collection("User").document(hashedEmail);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SetLogedInUser(documentSnapshot.toObject(User.class));
            }
        });



        return view;

    }

    private void SetLogedInUser(User user) {

        logedInUser=user;

        if (logedInUser.getBalance().getBalance()==-1) SetupUserBalance();

    }

    private void SetupUserBalance() {

        logedInUser.setBalance(new Balance(0.0,0));

        dataBase.collection("User").document(hashedEmail)
                .set(logedInUser, SetOptions.merge());


    }
}
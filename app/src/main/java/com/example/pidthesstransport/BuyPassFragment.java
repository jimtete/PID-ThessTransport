package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class BuyPassFragment extends Fragment {

    public static FirebaseFirestore dataBase;
    public static FragmentManager fragmentManager;
    public User logedInUser;
    String hashedEmail;


    View view;
    TextView welcomeTextView;

    public BuyPassFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_buy_pass, container, false);

        hashedEmail = getArguments().getString("email");

        welcomeTextView = view.findViewById(R.id.textViewWelcomePass);


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
        logedInUser = user;
        GeneratedTextViews();

    }

    private void GeneratedTextViews() {

        welcomeTextView.setText("Καλως ήρθες \n"+logedInUser.getUsername());

    }
}
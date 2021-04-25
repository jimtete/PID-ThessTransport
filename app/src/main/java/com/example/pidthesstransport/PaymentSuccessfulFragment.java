package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


public class PaymentSuccessfulFragment extends Fragment {

    public static FirebaseFirestore dataBase;
    public static FragmentManager fragmentManager;
    public User logedInUser;
    String hashedEmail;
    double amount;
    View view;
    EditText newBalanceEditText;
    Button homeButton;

    public PaymentSuccessfulFragment() {
        // Required empty public constructor
    }




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment_successful, container, false);


        hashedEmail = getArguments().getString("Email");
        amount = Double.parseDouble(getArguments().getString("Amount"));
        homeButton = view.findViewById(R.id.button_go_back_home);
        newBalanceEditText = view.findViewById(R.id.editText_new_balance);
        newBalanceEditText.setEnabled(false);
        newBalanceEditText.setInputType(InputType.TYPE_NULL);


        dataBase = FirebaseFirestore.getInstance();
        DocumentReference reference = dataBase.collection("User").document(hashedEmail);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SetLogedInUser(documentSnapshot.toObject(User.class));
            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OasthHomeActivity temp = (OasthHomeActivity)getActivity();
                fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, new HomeFragment());
                transaction.commit();


            }
        });
        return view;
    }

    private void SetLogedInUser(User user) {

        logedInUser = user;
        UpdateUserBalance(amount);

    }

    private void UpdateUserBalance(double amount) {


        logedInUser.AddBalance(amount);



        dataBase.collection("User").document(hashedEmail)
                .set(logedInUser, SetOptions.merge());

        newBalanceEditText.setText(logedInUser.getBalance().getBalance()+"€");
    }
}
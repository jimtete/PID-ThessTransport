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


public class PassPurchaseConfirmationFragment extends Fragment {

    public static FirebaseFirestore dataBase;
    public static FragmentManager fragmentManager;
    public User logedInUser;

    String hashedEmail,passDocument;

    TextView confirmationTextView;
    View view;
    Pass pass;
    ImageButton imageButton;

    public PassPurchaseConfirmationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pass_purchase_confirmation, container, false);
        dataBase = FirebaseFirestore.getInstance();
        hashedEmail = getArguments().getString("email");
        passDocument = getArguments().getString("passDocument");

        confirmationTextView = view.findViewById(R.id.textView_pass_confirmation);

        DocumentReference reference = dataBase.collection("User").document(hashedEmail);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SetLogedInUser(documentSnapshot.toObject(User.class));
            }
        });

        imageButton = view.findViewById(R.id.imageButton_continue_pass);

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

    private void SetLogedInUser(User user) {

        logedInUser = user;

        DocumentReference DocRef = dataBase.collection("Pass").document(passDocument);
        DocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                pass = documentSnapshot.toObject(Pass.class);
                UpdateUserBalance();
            }
        });


    }

    private void UpdateUserBalance() {

        double sendAmount = pass.getPrice();

        if (logedInUser.getBalance().getBalance()<sendAmount){
            ErrorOnPurchase(-1);
            return;
        }

        //Pass ID GENERATOR implementation
        int p[] = new int[16];
        PassIdGenerator.fill(p);
        String part1 = PassIdGenerator.toString(p);
        int value = PassIdGenerator.GenerateValue(part1);
        String part2 = PassIdGenerator.GenerateSecondString(16,value);

        String passId = part1+part2;



        
        PassHistory temp = logedInUser.getPassHistory();
        PassPurchases PPtemp = new PassPurchases(passId,pass);

        System.out.println(PPtemp.getPassIdentificator());
        temp.AddPurchase(PPtemp);

        logedInUser.BuyPass(sendAmount);
        logedInUser.setPassHistory(temp);

        dataBase.collection("User").document(hashedEmail)
                .set(logedInUser, SetOptions.merge());

        dataBase.collection("PassPurchases").
                document(passId).
                set(PPtemp);


        OasthHomeActivity activity = (OasthHomeActivity)getActivity();
        activity.logedInUser = logedInUser;

        ErrorOnPurchase(-2);

    }

    private void ErrorOnPurchase(int i) {
        switch (i){
            case -1:
                confirmationTextView.setText("Δεν έχετε αρκετό υπόλοιπο για να πραγματοποιηθεί η αγορά");
                break;
            case -2:
                confirmationTextView.setText("Το εισητήριο αγοράσθηκε με επιτυχία!");
                break;
        }

    }
}
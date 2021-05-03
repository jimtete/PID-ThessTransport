package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


public class BalanceFragment extends Fragment implements View.OnClickListener {


    public static FirebaseFirestore dataBase;
    public static FragmentManager fragmentManager;
    public User logedInUser;
    String hashedEmail;
    EditText walletEditText,pointsEditText;
    View view;

    EditText customAmountEditText;
    Button button1,button2,button5,buttonC;

    public BalanceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_balance, container, false);



        hashedEmail = getArguments().getString("email");

        customAmountEditText = view.findViewById(R.id.editText_custom_amount);

        button1 = view.findViewById(R.id.button_euro1);
        button2 = view.findViewById(R.id.button_euro2);
        button5 = view.findViewById(R.id.button_euro5);
        buttonC = view.findViewById(R.id.button_custom_euro);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button5.setOnClickListener(this);
        buttonC.setOnClickListener(this);


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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_euro1:
                DoPayment(1);
                break;
            case R.id.button_euro2:
                DoPayment(2);
                break;
            case R.id.button_euro5:
                DoPayment(5);
                break;
            case R.id.button_custom_euro:
                if(CheckEditBox()){
                    double amount;
                    amount = Double.parseDouble(customAmountEditText.getText().toString());
                    DoPayment(amount);
                }else{
                    Toast.makeText(getContext(),"Insert a valid amount",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean CheckEditBox() {
        double amount;
        String amountString = customAmountEditText.getText().toString();
        if (amountString.length()==0){
            Toast.makeText(getContext(),"Please insert a valid amount",Toast.LENGTH_SHORT).show();
            return false;
        }

        amount = Double.parseDouble(amountString);

        if (amount<=0){
            Toast.makeText(getContext(),"Amount can't be lower or equal to 0",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (amount<1){
            Toast.makeText(getContext(),"Smallest amount is 1 euro",Toast.LENGTH_SHORT).show();
            return false;
        }

        if ((amount*1000)%10!=0){
            return false;
        }

        return true;
    }

    private void SetLogedInUser(User user) {

        logedInUser=user;
        bundle.putString("email",stringHasher.CreateHash(logedInUser.getEmail()));
        if (logedInUser.getBalance().getBalance()==-1) SetupUserBalance();

        GenerateData();

    }

    private void GenerateData() {

        walletEditText = view.findViewById(R.id.editText_wallet);
        pointsEditText = view.findViewById(R.id.editText_points);
        SetEditText(walletEditText,logedInUser.getBalance().getBalance());
        SetEditText(pointsEditText,logedInUser.getBalance().getPoints());

    }

    private void SetEditText(EditText ET, double number){
        ET.setFocusable(false);
        ET.setEnabled(false);
        ET.setText(number+"");
    }

    private void SetupUserBalance() {

        logedInUser.setBalance(new Balance(0.0,0));
        logedInUser.setPassHistory(new PassHistory());

        dataBase.collection("User").document(hashedEmail)
                .set(logedInUser, SetOptions.merge());


    }

    Bundle bundle = new Bundle();
    private void DoPayment(double amount){

        Fragment selectedFragment = null;

        bundle.putString("Email",stringHasher.CreateHash(logedInUser.getEmail()));
        bundle.putString("Amount",amount+"");

        selectedFragment = new PaymentSuccessfulFragment();
        selectedFragment.setArguments(bundle);

        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, selectedFragment);
        transaction.commit();

    }
}
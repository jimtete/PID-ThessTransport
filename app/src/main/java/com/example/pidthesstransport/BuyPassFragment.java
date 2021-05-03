package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    String passDoc1,passDoc2;


    EditText buyPassWalletEditText;
    View view;
    RadioGroup durationRadioGroup;
    TextView welcomeTextView,calculatedCostTextView;
    CheckBox discountCheckBox;

    public BuyPassFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_buy_pass, container, false);

        RadioButton R1 = view.findViewById(R.id.radioButton_30D);
        R1.setChecked(true);
        passDoc1="30D";
        passDoc2="ON";
        hashedEmail = getArguments().getString("email");

        welcomeTextView = view.findViewById(R.id.textViewWelcomePass);

        discountCheckBox = view.findViewById(R.id.checkBox_discount);
        discountCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (discountCheckBox.isChecked()){
                    discount=true;
                    passDoc2="OFF";
                }else{
                    discount=false;
                    passDoc2="ON";
                }
                CalculateCostForTV();
            }
        });

        durationRadioGroup = view.findViewById(R.id.radioGroup_duration);
        durationRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_30D:
                        passDoc1="30D";
                        price=30;
                        CalculateCostForTV();
                        break;
                    case R.id.radioButton_90D:
                        passDoc1="90D";
                        price=75;
                        CalculateCostForTV();
                        break;
                    case R.id.radioButton_180D:
                        passDoc1="180D";
                        price=135;
                        CalculateCostForTV();
                        break;
                }
            }
        });


        buyPassWalletEditText = view.findViewById(R.id.editText_current_balance_pass);
        buyPassWalletEditText.setEnabled(false);

        calculatedCostTextView = view.findViewById(R.id.textView_calculated_cost);
        CalculateCostForTV();


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

    private void CalculateCostForTV() {

        calculatedCostTextView.setText("Κόστος πάσου : "+price+"€");
        if(discount)calculatedCostTextView.setText("Κόστος πάσου : "+price/2+"€");


    }

    private void SetLogedInUser(User user) {
        logedInUser = user;
        buyPassWalletEditText.setText("Πορτοφόλι : "+logedInUser.getBalance().getBalance()+"€");
        GeneratedTextViews();


    }

    private void GeneratedTextViews() {

        welcomeTextView.setText("Καλως ήρθες \n"+logedInUser.getUsername());

    }

    boolean discount = false;
    double price = 30;

}
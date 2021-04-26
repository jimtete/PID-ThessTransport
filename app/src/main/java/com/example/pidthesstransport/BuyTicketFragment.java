package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class BuyTicketFragment extends Fragment implements View.OnClickListener {

    User logedInUser;
    TextView busLineDescriptionTextView;
    EditText busLineNumberEditText,buyTicketWalletEditText;
    String QRScan, selectedBalance;
    View view;

    Button e050,e060,e100,e120,confirmButton;

    //Scanned Bee You Esh
    Bus scannedBus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_buy_ticket, container, false);

        OasthHomeActivity activity = (OasthHomeActivity)getActivity();
        logedInUser = activity.logedInUser;


        busLineNumberEditText = view.findViewById(R.id.editText_BusLine_Number);
        buyTicketWalletEditText = view.findViewById(R.id.editText_buy_ticket_wallet);
        busLineDescriptionTextView = view.findViewById(R.id.textView_BusLine_Description);

        e050 = view.findViewById(R.id.button_e050);e060 = view.findViewById(R.id.button_e060);
        e100 = view.findViewById(R.id.button_e100);e120 = view.findViewById(R.id.button_e120);
        confirmButton = view.findViewById(R.id.button_confirm_buy_ticket);

        confirmButton.setEnabled(false);

        e050.setOnClickListener(this);e060.setOnClickListener(this);e100.setOnClickListener(this);
        e120.setOnClickListener(this);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Fragment fragment = new TicketPurchaseConfirmationFragment();

                bundle.putString("Email", stringHasher.CreateHash(logedInUser.getEmail()));
                bundle.putString("Ticket",selectedBalance);
                bundle.putString("Line",scannedBus.getBusReg());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });



        QRScan = getArguments().getString("QRSCAN");



        scannedBus = new Bus(QRScan.substring(0,QRScan.indexOf(":"))
                ,new BusLine(
                Integer.parseInt(QRScan.substring(QRScan.indexOf(":")+1,QRScan.lastIndexOf(":")))
                ,QRScan.substring(QRScan.lastIndexOf(":")+1)
        ));


        busLineNumberEditText.setEnabled(false);
        busLineNumberEditText.setText(scannedBus.getBusLine().getNumber()+"");

        buyTicketWalletEditText.setEnabled(false);
        buyTicketWalletEditText.setText(logedInUser.getBalance().getBalance()+"");

        busLineDescriptionTextView.setText(scannedBus.getBusLine().getDescription());





        return view;
    }

    public void EnableConfirmButton(String s){
        confirmButton.setEnabled(true);

        TextView temp = view.findViewById(R.id.textView_chosen_ticket);
        temp.setText(s+" κόστος: "+selectedBalance);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_e050:
                selectedBalance = e050.getText().toString();
                EnableConfirmButton("Επιλέξατε μειωμένο εισητήριο μίας διαδρομής με");
                break;
            case R.id.button_e060:
                selectedBalance = e060.getText().toString();
                EnableConfirmButton("Επιλέξατε μειωμένο εισητήριο διπλής διαδρομής με");
                break;
            case R.id.button_e100:
                selectedBalance = e100.getText().toString();
                EnableConfirmButton("Επιλέξατε κανονικό εισητήριο μίας διαδρομής με");
                break;
            case R.id.button_e120:
                selectedBalance = e120.getText().toString();
                EnableConfirmButton("Επιλέξατε κανονικό εισητήριο διπλής διαδρομής με");
                break;
        }
    }


}
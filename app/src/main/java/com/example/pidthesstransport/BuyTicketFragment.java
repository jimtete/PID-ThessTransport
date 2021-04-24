package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class BuyTicketFragment extends Fragment {

    User logedInUser;
    TextView busLineDescriptionTextView;
    EditText busLineNumberEditText,buyTicketWalletEditText;
    String QRScan;
    View view;

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
}
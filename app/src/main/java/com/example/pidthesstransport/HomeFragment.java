package com.example.pidthesstransport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    TextView testTextView;
    String hashedEmail;
    User logedInUser;
    View view;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        OasthHomeActivity activity = (OasthHomeActivity)getActivity();
        logedInUser = activity.logedInUser;
        hashedEmail = stringHasher.CreateHash(logedInUser.getEmail());

        testTextView = view.findViewById(R.id.test);
        testTextView.setText(""+hashedEmail);



        return view;
    }
}
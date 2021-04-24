package com.example.pidthesstransport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE=100;
    public static FirebaseFirestore dataBase;
    Button registerButton, loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = FirebaseFirestore.getInstance();
        PutBusLines();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);




        registerButton= findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });






    }

    private void PutBusLines() {

        BusLine  N35 = new BusLine(35,"ΜΕΤΕΩΡΑ ΒΕΝΙΖΕΛΟΥ");
        BusLine  N64 = new BusLine(64,"ΦΙΛΥΡΟ - Ν.Σ.Σταθμός");
        BusLine  N52 = new BusLine(52, "Ν.Σ.Σταθμός - ΔΙ.ΠΑ.Ε. Αλεξάνδρεια Παν/πολή");

        Bus B1 = new Bus("NZXT-388",N52);
        System.out.println(B1.SerializeBusInfo());
//        dataBase.collection("Buses").
//                document("NZXT-388").
//                set(B1);

    }


}
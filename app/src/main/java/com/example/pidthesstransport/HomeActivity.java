package com.example.pidthesstransport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AutomaticZenRule;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    public static FirebaseFirestore dataBase;
    public User logedInUser;
    Button oasth_button;
    Button trainose_button;
    Button ktel_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String hashedEmail = intent.getStringExtra("USER");
        dataBase = FirebaseFirestore.getInstance();




        oasth_button = findViewById(R.id.oasth_button);
        trainose_button = findViewById(R.id.trainose_button);
        ktel_button = findViewById(R.id.ktel_button);

        oasth_button.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, OasthHomeActivity.class);
            intent1.putExtra("USER",hashedEmail);
            startActivity(intent1);
        });

        trainose_button.setEnabled(false);
        ktel_button.setEnabled(false);



    }


}
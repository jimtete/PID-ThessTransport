package com.example.pidthesstransport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OasthHomeActivity extends AppCompatActivity {
    public static FirebaseFirestore dataBase;
    public User logedInUser;
    Button purchase_ticket_button;
    Button purchase_pass_button;
    Button profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oasth_home);

        Intent intent = getIntent();
        String hashedEmail = intent.getStringExtra("USER");
        dataBase = FirebaseFirestore.getInstance();

        DocumentReference reference = dataBase.collection("User").document(hashedEmail);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SetLogedInUser(documentSnapshot.toObject(User.class));
            }
        });

        purchase_ticket_button = findViewById(R.id.purchase_ticket_button);
        purchase_pass_button = findViewById(R.id.purchase_pass_button);
        profile_button = findViewById(R.id.profile_button);
    }

    public void SetLogedInUser(User toObject) {
        logedInUser=toObject;
        TextView textView = findViewById(R.id.Text_Services);
        textView.setText("Oasth Home Say Hello : \n"+logedInUser.getUsername());
    }

}
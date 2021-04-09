package com.example.pidthesstransport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {


    public static FirebaseFirestore dataBase;

    Button loginButton;
    EditText emailEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dataBase = FirebaseFirestore.getInstance();
        emailEditText = findViewById(R.id.editText_login_email);
        passwordEditText = findViewById(R.id.editText_login_password);


        loginButton = findViewById(R.id.button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,hashedEmail;

                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                hashedEmail = stringHasher.CreateHash(email);

                DocumentReference reference = dataBase.collection("User").document(hashedEmail);
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                User user = document.toObject(User.class);
                                if (stringHasher.CreateHash(password).equals(user.getPassword())) changeUI(user.getEmail());
                            }else{
                                System.out.println("It doesn't");
                            }
                        }else{
                            Log.d("FAILURE: ", "Exception: ",task.getException());
                        }


                    }
                });





            }
        });


    }

    private void changeUI(String userHash) {

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("USER", stringHasher.CreateHash(userHash));
        startActivity(intent);
    }


}
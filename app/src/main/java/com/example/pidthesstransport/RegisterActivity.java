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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RegisterActivity extends AppCompatActivity {

    public static FirebaseFirestore dataBase;
    EditText username,password,email;
    Button finishButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dataBase = FirebaseFirestore.getInstance();

        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        email = findViewById(R.id.editText_email);

        finishButton = findViewById(R.id.finish_register_button);

        finishButton.setOnClickListener(v -> {

            emailExists=false;
            usernameExists=false;
            if (username.getText().toString().length()<8){
                Toast.makeText(getApplicationContext(),"Username must have at least 8 characters",Toast.LENGTH_SHORT).
                        show();
                return ;
            }
            if (password.getText().toString().length()<8){
                Toast.makeText(getApplicationContext(),"Password must have at least 8 characters",Toast.LENGTH_SHORT).
                        show();
                return ;
            }

           if (checkUser(username.getText().toString(),email.getText().toString())){
               return;
           }
            else {
               User temp = new User(username.getText().toString(),
                       stringHasher.CreateHash(password.getText().toString()),
                       email.getText().toString());

               dataBase.collection("User")
                       .document(stringHasher.CreateHash(email.getText().toString()))
                       .set(temp)
                       .addOnCompleteListener((task -> {
                           Toast.makeText(getApplicationContext(), "Succesfully registered. please login", Toast.LENGTH_LONG).
                                   show();
                           Intent intent = new Intent(this, MainActivity.class);
                       })).addOnFailureListener(e -> {
                   Toast.makeText(getApplicationContext(), "Couldn't register, please contact the administrator.", Toast.LENGTH_LONG).show();
               });
           }





        });

    }

    private boolean checkUser(String username, String email) {

        DocumentReference documentReference = dataBase.collection("User").document(stringHasher.CreateHash(email));

        documentReference.get().addOnCompleteListener(task -> {
            DocumentSnapshot document = task.getResult();
            if (document.exists()){
                Toast.makeText(getApplicationContext(),"There is already an account with the same email",Toast.LENGTH_SHORT).show();
                emailExists = true;
            }else{
                emailExists = false;
            }
        });

        dataBase.collection("User")
                .whereEqualTo("username",username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"There is already an account with the same username",Toast.LENGTH_SHORT).show();
                            usernameExists = true;
                        } else {
                            usernameExists = false;
                        }


                    }
                });


        return (emailExists||usernameExists);
    }

    boolean emailExists;
    boolean usernameExists;
}
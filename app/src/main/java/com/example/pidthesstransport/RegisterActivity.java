package com.example.pidthesstransport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    DocumentReference documentReference;
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


            String un = username.getText().toString();
            String em = email.getText().toString();
            if (un.length()<8){
                Toast.makeText(getApplicationContext(),"Username must have at least 8 characters",Toast.LENGTH_SHORT).
                        show();
                return ;
            }
            if (password.getText().toString().length()<8){
                Toast.makeText(getApplicationContext(),"Password must have at least 8 characters",Toast.LENGTH_SHORT).
                        show();
                return ;
            }

            User temp = new User(un,
                    stringHasher.CreateHash(password.getText().toString()),
                    em);

            DocumentReference reference = dataBase.collection("User").document(stringHasher.CreateHash(em));
            reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            exists= true;
                            Toast.makeText(getApplicationContext(),"There is already an account with the same email", Toast.LENGTH_SHORT).show();
                        } else {
                            exists = false;
                            dataBase.collection("User").
                                    document(stringHasher.CreateHash(em)).
                                    set(temp).
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Succesfully registered. please login", Toast.LENGTH_LONG).
                                                    show();

                                        }
                                    });
                        }
                    } else {
                        Log.d("Failure", "get failed with ", task.getException());
                    }

                }
            });



//               dataBase.collection("User")
//                       .document(stringHasher.CreateHash(em))
//                       .set(temp)
//                       .addOnCompleteListener((task -> {
//                           Toast.makeText(getApplicationContext(), "Succesfully registered. please login", Toast.LENGTH_LONG).
//                                   show();
//                           Intent intent = new Intent(this, MainActivity.class);
//                           startActivity(intent);
//                       })).addOnFailureListener(e -> {
//                   Toast.makeText(getApplicationContext(), "Couldn't register, please contact the administrator.", Toast.LENGTH_LONG).show();
//               });






        });

    }

    @SuppressLint("ShowToast")
    private boolean checkUser(String username, String email) {


        exists = false;






        return exists;
    }

    public boolean exists;

}
package com.example.pidthesstransport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OasthHomeActivity extends AppCompatActivity {
    public static FirebaseFirestore dataBase;
    public User logedInUser;
    public static FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oasth_home);
        Intent intent = getIntent();
        String hashedEmail = intent.getStringExtra("USER");
        dataBase = FirebaseFirestore.getInstance();

        //Done loading the activity

        //Getting set up with the fragment and the bottom nav bar


        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);




        //Setting the data
        DocumentReference reference = dataBase.collection("User").document(hashedEmail);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SetLogedInUser(documentSnapshot.toObject(User.class));
            }
        });




        Fragment selectedFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.buy_ticket:
                            break;
                        case R.id.buy_pass:
                            break;
                        case R.id.balance:
                            selectedFragment = new BalanceFragment();
                            break;
                    }
                    selectedFragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
    };


    Bundle bundle = new Bundle();
    public void SetLogedInUser(User user) {
        logedInUser=user;
        bundle.putString("email",stringHasher.CreateHash(logedInUser.getEmail()));
    }



}
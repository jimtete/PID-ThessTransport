package com.example.pidthesstransport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTextView=findViewById(R.id.info);
        infoTextView.setText("Geia soy Tete<3");

    }
    TextView infoTextView;
}
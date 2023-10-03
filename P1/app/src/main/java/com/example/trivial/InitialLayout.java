package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialLayout extends AppCompatActivity {

    Button iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_layout);
        iniciar = findViewById(R.id.buttonIniciar);
    }
    public void startTrivial(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
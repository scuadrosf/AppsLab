package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InitialActivity extends AppCompatActivity {

    Button start;
    private Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_layout);
        start = findViewById(R.id.buttonIniciar);

        helpButton = findViewById(R.id.helpButton);





    }
    public void startTrivial(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void help (View view){
        Intent intent = new Intent(this, UserManualActivity.class);
        startActivity(intent);
    }

}
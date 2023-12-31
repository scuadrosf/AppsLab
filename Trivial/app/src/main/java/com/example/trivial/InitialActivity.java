package com.example.trivial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

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
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, helpButton, "transition_help_button");
        Intent intent = new Intent(this, UserManualActivity.class);
        startActivity(intent, options.toBundle());
    }

}
package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        if (intent.hasExtra("SCORE_FINAL")){
            finalScore = intent.getIntExtra("SCORE_FINAL", 0);
        }

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        String showPuntutation = "Puntuacion " + finalScore;
        scoreTextView.setText(showPuntutation);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Cierra todas las actividades anteriores
                startActivity(intent);
                finish(); // Finaliza la actividad actual
            }
        });
    }
}
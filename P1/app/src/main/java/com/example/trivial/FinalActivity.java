package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;

public class FinalActivity extends AppCompatActivity {

    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        if (intent.hasExtra("SCORE_FINAL")) {
            finalScore = intent.getIntExtra("SCORE_FINAL", 0);
        }

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        String showPunctuation = "Puntuación: " + finalScore;
        scoreTextView.setText(showPunctuation);

        LottieAnimationView successAnimation = findViewById(R.id.animation_view);
        LottieAnimationView failureAnimation = findViewById(R.id.animation_failure);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        // Mostrar animación según la puntuación
        if (finalScore >= 8) {
            // Mostrar animación de éxito y ocultar la de fallo
            successAnimation.setVisibility(View.VISIBLE);
            successAnimation.playAnimation();

            failureAnimation.setVisibility(View.GONE);
        } else {
            // Mostrar animación de fallo y ocultar la de éxito
            failureAnimation.setVisibility(View.VISIBLE);
            failureAnimation.playAnimation();

            successAnimation.setVisibility(View.GONE);
        }
    }
}

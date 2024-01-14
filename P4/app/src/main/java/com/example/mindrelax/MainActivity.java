package com.example.mindrelax;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LottieAnimationView lottieMeditation = findViewById(R.id.lottieAnimationMeditation);
        LottieAnimationView lottieNature = findViewById(R.id.lottieAnimationNatureSounds);
        LottieAnimationView lottieBreathing = findViewById(R.id.lottieAnimationBreathingExercises);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Button buttonMeditation = findViewById(R.id.buttonMeditation);
        Button buttonNatureSounds = findViewById(R.id.buttonNatureSounds);
        Button buttonBreathingExercises = findViewById(R.id.buttonBreathingExercises);
        ImageButton logoutButton = findViewById(R.id.logoutButton);
        ImageButton profileButton = findViewById(R.id.profileButton);

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        });

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
        });

        buttonMeditation.setOnClickListener(v -> {
            lottieMeditation.playAnimation();
            lottieMeditation.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Intent intent = new Intent(MainActivity.this, Meditacion.class);
                    startActivity(intent);
                }
            });
        });

        buttonNatureSounds.setOnClickListener(v -> {
            lottieNature.playAnimation();
            lottieNature.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Intent intent = new Intent(MainActivity.this, Naturaleza.class);
                    startActivity(intent);
                }
            });
        });

        buttonBreathingExercises.setOnClickListener(v -> {
            lottieBreathing.playAnimation();
            lottieBreathing.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Intent intent = new Intent(MainActivity.this, Respiracion.class);
                    startActivity(intent);
                }
            });
        });

        ImageView imageViewAdditional = findViewById(R.id.ayudar);
        imageViewAdditional.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Help.class);
            startActivity(intent);
        });
    }
}

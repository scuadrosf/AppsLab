package com.example.mindrelax;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Meditacion extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final List<String> meditationSteps = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditacion);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(Meditacion.this, MainActivity.class);
            startActivity(intent);
        });

        ToggleButton playSoundToggleButton = findViewById(R.id.btnPlaySound);
        playSoundToggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startPlayingSound();
            } else {
                stopPlayingSound();
            }
        });

        meditationSteps.add(getString(R.string.step_1_description));
        meditationSteps.add(getString(R.string.step_2_description));
        meditationSteps.add(getString(R.string.step_3_description));
        meditationSteps.add(getString(R.string.step_4_description));
        meditationSteps.add(getString(R.string.step_5_description));
        meditationSteps.add(getString(R.string.step_6_description));
        meditationSteps.add(getString(R.string.step_7_description));
        meditationSteps.add(getString(R.string.step_8_description));
        meditationSteps.add(getString(R.string.step_9_description));
        meditationSteps.add(getString(R.string.step_10_description));

        // Aqui es donde se añaden los pasos de forma dinamica
        LinearLayout layoutSteps = findViewById(R.id.layoutSteps);

        // Crea y añade las vistas de texto para cada paso
        for (String step : meditationSteps) {
            TextView stepTextView = new TextView(this);
            stepTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            stepTextView.setText(step);
            stepTextView.setTextSize(18);
            // Puedes personalizar más las TextViews aquí como quieras

            layoutSteps.addView(stepTextView);
        }
    }

    private void startPlayingSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.meditacion);
            mediaPlayer.setLooping(true);
        }
        mediaPlayer.start();
    }

    private void stopPlayingSound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
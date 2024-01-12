package com.example.mindrelax;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Si tu Activity ya tiene una ActionBar por defecto, ocúltala.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Opcional: desactivar el título predeterminado
        }

        Button buttonMeditation = findViewById(R.id.buttonMeditation);
        Button buttonNatureSounds = findViewById(R.id.buttonNatureSounds);
        Button buttonBreathingExercises = findViewById(R.id.buttonBreathingExercises);

        buttonMeditation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Meditacion.class);
            startActivity(intent);
        });

        buttonNatureSounds.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Naturaleza.class);
            startActivity(intent);
        });

        buttonBreathingExercises.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Respiracion.class);
            startActivity(intent);
        });

        ImageView imageViewAdditional = findViewById(R.id.ayudar);
        imageViewAdditional.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Help.class);
            startActivity(intent);
        });
    }
}

package com.example.mindrelax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Naturaleza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naturaleza);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            pauseFragmentSounds();
            Intent intent = new Intent(Naturaleza.this, MainActivity.class);
            startActivity(intent);
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer1, new NatureSoundsFragment1())
                    .add(R.id.fragmentContainer2, new NatureSoundsFragment2())
                    .commit();
        }
    }

    private void pauseFragmentSounds(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        NatureSoundsFragment1 fragment1 = (NatureSoundsFragment1) fragmentManager.findFragmentById(R.id
                .fragmentContainer1);
        if (fragment1 != null) {
            fragment1.pauseAllSounds();
        }
        NatureSoundsFragment2 fragment2 = (NatureSoundsFragment2) fragmentManager.findFragmentById(R.id.fragmentContainer2);
        if (fragment2 != null) {
            fragment2.pauseAllSounds();
        }
    }
}


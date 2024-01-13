package com.example.mindrelax;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Respiracion extends AppCompatActivity {

    private final List<String> breathSteps = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiracion);

        // Inicialización de componentes de la UI
        ImageButton backButton = findViewById(R.id.btnBack);
        ImageButton playButton = findViewById(R.id.playButton);
        ImageButton pauseButton = findViewById(R.id.pauseButton);
        ImageButton forwardButton = findViewById(R.id.forwardButton);
        ImageButton backwardButton = findViewById(R.id.backwardButton);

        // Configurar MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.relajacion);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        // Actualiza el SeekBar conforme avanza el sonido
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                }
                handler.postDelayed(this, 1000);
            }
        });
        // Listener para el SeekBar
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Configurar botones
        playButton.setOnClickListener(v -> mediaPlayer.start());
        pauseButton.setOnClickListener(v -> mediaPlayer.pause());
        forwardButton.setOnClickListener(v -> mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000)); // Avanza 10 segundos
        backwardButton.setOnClickListener(v -> mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000)); // Retrasa 10 segundos
        backButton.setOnClickListener(v -> {
            mediaPlayer.pause();
            Intent intent = new Intent(Respiracion.this, MainActivity.class);
            startActivity(intent);
        });

        breathSteps.add("Encuentra un lugar tranquilo que consideres personal de calma");
        breathSteps.add("Va a poder hacerlo tumbado o sentado, lo que prefiera, si está tumbado pondra los " +
                "brazos estirados, con las palmas hacia arriba o abajo, según esté más comodo. Si está sentado" +
                "intente mantener la espalda recta y no hundirse en el respaldo y ponga las manos en las rodillas. " +
                "Si a lo largo del ejercicio necesita reajustar la posición, se hace, no pasa nada.");
        breathSteps.add("Si tiene los ojos cerrados es más fácil que no se distraiga, pero si prefiere tnerlos" +
                " abiertos puede hacerlo, aunque le recomiendo que entonces intente desenfocar la mirara " +
                "y no fijarla en nada en concreto");
        breathSteps.add("No hace falta ninguna experiencia previa y con la práctica, le resultará cada vez " +
                "más sencillo no despistarse mientras lo hace.");
        breathSteps.add("Pensar en otras cosas y perder la atención es algo completamente normal, intentaremos ser " +
                "conscientes del pensamiento que nos haya interrumpido y volver a concentrarnos en la voz del ejercicio");
        breathSteps.add("ES MOMENTO DE COMENZAR. DALE AL PLAY");

        LinearLayout layoutSteps = findViewById(R.id.layoutBreath);

        for (String step : breathSteps) {
            TextView stepTextView = new TextView(this);
            stepTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            stepTextView.setText(step);
            stepTextView.setTextSize(18);
            stepTextView.setTextColor(getColor(R.color.black));

            layoutSteps.addView(stepTextView);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}

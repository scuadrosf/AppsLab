package com.example.mindrelax;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

public class NatureSoundsFragment2 extends Fragment {

    private final MediaPlayer[] mediaPlayers = new MediaPlayer[5];
    private final int[] soundResIds = new int[]{
            R.raw.fuego, R.raw.tormenta, R.raw.piano, R.raw.riachuelo, R.raw.ballenas
    };
    private final int[] buttonIds = new int[]{
            R.id.buttonSound6, R.id.buttonSound7, R.id.buttonSound8, R.id.buttonSound9, R.id.buttonSound10
    };
    private final int[] imageResIds = new int[]{
            R.drawable.fuego, R.drawable.tormenta, R.drawable.piano, R.drawable.riachuelo, R.drawable.ballena
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nature_sounds2, container, false);
        for (int i = 0; i < buttonIds.length; i++) {
            mediaPlayers[i] = MediaPlayer.create(getContext(), soundResIds[i]);
            ToggleButton toggleButton = view.findViewById(buttonIds[i]);
            final int index = i;
            toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    playSound(index);
                } else {
                    pauseSound(index);
                }
            });
        }
        return view;
    }

    private void playSound(int index) {
        // Detener otros sonidos
        for (int i = 0; i < mediaPlayers.length; i++) {
            if (i != index && mediaPlayers[i].isPlaying()) {
                mediaPlayers[i].pause();
            }
        }
        // Iniciar el sonido seleccionado
        MediaPlayer player = mediaPlayers[index];
        if (!player.isPlaying()) {
            player.start();
        }
        showImage(index);
    }

    private void pauseSound(int index) {
        // Pausar el sonido si está reproduciéndose
        MediaPlayer player = mediaPlayers[index];
        if (player.isPlaying()) {
            player.pause();
        }
        hideImage();
    }
    public void pauseAllSounds() {
        for (MediaPlayer mp : mediaPlayers) {
            if (mp != null && mp.isPlaying()) {
                mp.pause();
            }
        }
    }
    private void showImage(int index) {
        ImageView imageView = requireActivity().findViewById(R.id.imageView2);
        imageView.setImageResource(imageResIds[index]);
        imageView.setVisibility(View.VISIBLE);
    }
    private void hideImage() {
        ImageView imageView = requireActivity().findViewById(R.id.imageView2);
        imageView.setVisibility(View.GONE);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        for (MediaPlayer mp : mediaPlayers) {
            if (mp != null) {
                if (mp.isPlaying()) {
                    mp.stop();
                }
                mp.release();
            }
        }
    }
}


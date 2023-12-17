package com.ldm.futsnakeball.juego;

import com.ldm.futsnakeball.Pantalla;
import com.ldm.futsnakeball.androidimpl.AndroidJuego;

public class JuegoFutsnakeball extends AndroidJuego {
    @Override
    public Pantalla getStartScreen() {
        return new LoadingScreen(this);
    }
}

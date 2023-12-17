package com.ldm.futsnakeball.juego;

import com.ldm.futsnakeball.Graficos;
import com.ldm.futsnakeball.Juego;
import com.ldm.futsnakeball.Pantalla;

public class LoadingScreen extends Pantalla {
    public LoadingScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        Graficos g = juego.getGraphics();
        Assets.fondo = g.newPixmap("fondo.png", Graficos.PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.menuprincipal = g.newPixmap("menuprincipal.png", Graficos.PixmapFormat.ARGB4444);
        Assets.bota = g.newPixmap("bota.png", Graficos.PixmapFormat.ARGB4444);

        Assets.flechaDrch = g.newPixmap("flechaDrch.png", Graficos.PixmapFormat.ARGB4444);
        Assets.flechaIzq = g.newPixmap("flechaIzq.png", Graficos.PixmapFormat.ARGB4444);
        Assets.parar = g.newPixmap("stop.png", Graficos.PixmapFormat.ARGB4444);
        Assets.cerrar = g.newPixmap("exit.png", Graficos.PixmapFormat.ARGB4444);
        Assets.muted = g.newPixmap("siMuted.png", Graficos.PixmapFormat.ARGB4444);
        Assets.noMuted = g.newPixmap("noMuted.png", Graficos.PixmapFormat.ARGB4444);

        Assets.ayuda1 = g.newPixmap("ayuda1.png", Graficos.PixmapFormat.ARGB4444);
        Assets.ayuda2 = g.newPixmap("ayuda2.png", Graficos.PixmapFormat.ARGB4444);
        Assets.ayuda3 = g.newPixmap("ayuda3.png", Graficos.PixmapFormat.ARGB4444);
        Assets.numeros = g.newPixmap("numeros.png", Graficos.PixmapFormat.ARGB4444);

        Assets.preparado = g.newPixmap("preparado.png", Graficos.PixmapFormat.ARGB4444);
        Assets.finjuego = g.newPixmap("finjuego.png", Graficos.PixmapFormat.ARGB4444);

        Assets.menupausa = g.newPixmap("menupausa.png", Graficos.PixmapFormat.ARGB4444);

        Assets.futPresent = g.newPixmap("futpresent.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fut2Present = g.newPixmap("fut2present.png", Graficos.PixmapFormat.ARGB4444);

        Assets.futArriba = g.newPixmap("futArriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.futIzquierda = g.newPixmap("futIzq.png", Graficos.PixmapFormat.ARGB4444);
        Assets.futAbajo = g.newPixmap("futAbajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.futDerecha = g.newPixmap("futDrch.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fut2Arriba = g.newPixmap("fut2arriba.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fut2Izquierda = g.newPixmap("fut2izq.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fut2Abajo = g.newPixmap("fut2abajo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.fut2Derecha = g.newPixmap("fut2drch.png", Graficos.PixmapFormat.ARGB4444);
        Assets.goles = g.newPixmap("golazo.png", Graficos.PixmapFormat.ARGB4444);
        Assets.premio1 = g.newPixmap("premio1.png", Graficos.PixmapFormat.ARGB4444);
        Assets.premio2 = g.newPixmap("premio2.png", Graficos.PixmapFormat.ARGB4444);
        Assets.premio3 = g.newPixmap("premio3.png", Graficos.PixmapFormat.ARGB4444);

        Assets.pulsar = juego.getAudio().nuevoSonido("pulsar.ogg");
        Assets.golazo = juego.getAudio().nuevoSonido("golazo.ogg");
        Assets.perder = juego.getAudio().nuevoSonido("perder.ogg");
        Assets.incorrecto = juego.getAudio().nuevoSonido("incorrect.ogg");

        Configuraciones.cargar(juego.getFileIO());
        juego.setScreen(new MainMenuScreen(juego));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
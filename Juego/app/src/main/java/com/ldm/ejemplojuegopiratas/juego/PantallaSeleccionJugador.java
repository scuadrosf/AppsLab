package com.ldm.ejemplojuegopiratas.juego;

import com.ldm.ejemplojuegopiratas.Graficos;
import com.ldm.ejemplojuegopiratas.Input;
import com.ldm.ejemplojuegopiratas.Juego;
import com.ldm.ejemplojuegopiratas.Pantalla;

import java.util.List;

public class PantallaSeleccionJugador extends Pantalla {

    boolean maleSelected = true;

    public PantallaSeleccionJugador(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x > 70 && event.y < 250 ) {
                    // Female
                    Configuraciones.selectPlayer(false);
                    juego.setScreen(new PantallaJuego(juego));
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    return;
                }
                if(event.x > 70 && event.x < 256 && event.y > 250  && event.y < 416) {
                    // Male
                    Configuraciones.selectPlayer(true);
                    juego.setScreen(new PantallaJuego(juego));
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    return;
                }
                if(event.x > 256 && event.y > 416 ) {

                    juego.setScreen(new MainMenuScreen(juego));
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();
        g.drawPixmap(Assets.fondo, -50, -70);
        g.drawPixmap(Assets.fut2Present, 70, 30);
        g.drawPixmap(Assets.futPresent, 120, 270);
        g.drawPixmap(Assets.cerrar, 256, 416);
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

package com.ldm.ejemplojuegopiratas.juego;
import java.util.List;
import com.ldm.ejemplojuegopiratas.Juego;
import com.ldm.ejemplojuegopiratas.Graficos;
import com.ldm.ejemplojuegopiratas.Input.TouchEvent;
import com.ldm.ejemplojuegopiratas.Pantalla;

public class MainMenuScreen extends Pantalla {
    public MainMenuScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void update(float deltaTime) {
        Graficos g = juego.getGraphics();
        List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
        juego.getInput().getKeyEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Configuraciones.sonidoHabilitado = !Configuraciones.sonidoHabilitado;
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(100);
                }
                if(inBounds(event, 64, 220, 192, 42) ) {
                    juego.setScreen(new PantallaJuego(juego));
                    if(Configuraciones.sonidoHabilitado)
                    {Assets.pulsar.play(1);
                    }

                    return;
                }
                if(inBounds(event, 64, 220 + 42, 192, 42) ) {
                    juego.setScreen(new PantallaMaximasPuntuaciones(juego));
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    return;
                }
                if(inBounds(event, 64, 220 + 84, 192, 42) ) {
                    juego.setScreen(new PantallaAyuda(juego));
                    if(Configuraciones.sonidoHabilitado)
                        Assets.pulsar.play(1);
                    return;
                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graficos g = juego.getGraphics();

        g.drawPixmap(Assets.fondo, -50, -70);
        g.drawPixmap(Assets.logo, 70, 20);
        g.drawPixmap(Assets.menuprincipal, -10, 120);
        if(Configuraciones.sonidoHabilitado)
            g.drawPixmap(Assets.noMuted, 0, 416);
        else
            g.drawPixmap(Assets.muted, 0, 390);
    }

    @Override
    public void pause() {
        Configuraciones.save(juego.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}


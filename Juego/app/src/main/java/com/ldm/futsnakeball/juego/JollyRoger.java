package com.ldm.futsnakeball.juego;

import java.util.ArrayList;
import java.util.List;

public class JollyRoger {
    public static final int ARRIBA = 0;
    public static final int IZQUIERDA= 1;
    public static final int ABAJO = 2;
    public static final int DERECHA = 3;

    public List<Balon> partes = new ArrayList<>();
    public int direccion;

    public JollyRoger() {
        direccion = ARRIBA;
        partes.add(new Balon(5, 6));
        partes.add(new Balon(5, 7));
        partes.add(new Balon(5, 8));
    }

    public void girarIzquierda() {
        direccion += 1;
        if(direccion > DERECHA)
            direccion = ARRIBA;
    }

    public void girarDerecha() {
        direccion -= 1;
        if(direccion < ARRIBA)
            direccion = DERECHA;
    }

    public void golazo() {
        Balon end = partes.get(partes.size()-1);
        partes.add(new Balon(end.x, end.y));
    }

    public void penalti() {
        Balon end = partes.get(partes.size()-1);
        partes.remove(end);
    }

    public void avance() {
        Balon balon = partes.get(0);

        int len = partes.size() - 1;
        for(int i = len; i > 0; i--) {
            Balon antes = partes.get(i-1);
            Balon parte = partes.get(i);
            parte.x = antes.x;
            parte.y = antes.y;
        }

        if(direccion == ARRIBA)
            balon.y -= 1;
        if(direccion == IZQUIERDA)
            balon.x -= 1;
        if(direccion == ABAJO)
            balon.y += 1;
        if(direccion == DERECHA)
            balon.x += 1;

        if(balon.x < 0)
            balon.x = 9;
        if(balon.x > 9)
            balon.x = 0;
        if(balon.y < 0)
            balon.y = 12;
        if(balon.y > 12)
            balon.y = 0;
    }

    public boolean comprobarChoque() {
        int len = partes.size();
        Balon balon = partes.get(0);
        for(int i = 1; i < len; i++) {
            Balon parte = partes.get(i);
            if(parte.x == balon.x && parte.y == balon.y)
                return true;
        }
        return false;
    }
}


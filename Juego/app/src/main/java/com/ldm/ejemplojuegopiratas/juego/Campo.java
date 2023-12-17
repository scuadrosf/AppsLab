package com.ldm.ejemplojuegopiratas.juego;

import java.util.Random;

public class Campo {
    static final int CAMPO_ANCHO = 10;
    static final int CAMPO_ALTO = 13;
    static final int INCREMENTO_PUNTUACION = 10;
    static final float TICK_INICIAL = 0.5f;
    static final float TICK_DECREMENTO = 0.05f;

    public JollyRoger jollyroger;
    public Premio premio;
    public boolean finalJuego = false;
    public int puntuacion = 0;

    boolean[][] campos = new boolean[CAMPO_ANCHO][CAMPO_ALTO];
    Random random = new Random();
    float tiempoTick = 0;
    static float tick = TICK_INICIAL;

    public Campo() {
        jollyroger = new JollyRoger();
        colocarPremio();
    }

    private void colocarPremio() {
        for (int x = 0; x < CAMPO_ANCHO; x++) {
            for (int y = 0; y < CAMPO_ALTO; y++) {
                campos[x][y] = false;
            }
        }

        int len = jollyroger.partes.size();
        for (int i = 0; i < len; i++) {
            Balon parte = jollyroger.partes.get(i);
            campos[parte.x][parte.y] = true;
        }

        int botinX = random.nextInt(CAMPO_ANCHO);
        int botinY = random.nextInt(CAMPO_ALTO);
        while (campos[botinX][botinY]) {
            botinX += 1;
            if (botinX >= CAMPO_ANCHO) {
                botinX = 0;
                botinY += 1;
                if (botinY >= CAMPO_ALTO) {
                    botinY = 0;
                }
            }
        }
        premio = new Premio(botinX, botinY, random.nextInt(4));
    }

    public void update(float deltaTime) {
        if (finalJuego)

            return;

        tiempoTick += deltaTime;

        while (tiempoTick > tick) {
            tiempoTick -= tick;
            jollyroger.avance();
            if (jollyroger.comprobarChoque()) {
                finalJuego = true;
                return;
            }

            Balon head = jollyroger.partes.get(0);
            if (head.x == premio.x && head.y == premio.y && premio.tipo != 3) {
                puntuacion += INCREMENTO_PUNTUACION;
                jollyroger.golazo();
                if (jollyroger.partes.size() == CAMPO_ANCHO * CAMPO_ALTO) {
                    finalJuego = true;
                    return;
                } else {
                    colocarPremio();
                }

                if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
                    tick -= TICK_DECREMENTO;
                }
            } else if (head.x == premio.x && head.y == premio.y) {
                puntuacion -= INCREMENTO_PUNTUACION;
                jollyroger.penalti();
                if (jollyroger.partes.size() == CAMPO_ANCHO * CAMPO_ALTO) {
                    finalJuego = true;
                    return;
                } else {
                    colocarPremio();
                }

                if (puntuacion % 100 == 0 && tick - TICK_DECREMENTO > 0) {
                    tick -= TICK_DECREMENTO;
                }

            }
        }
    }
}


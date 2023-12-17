package com.ldm.futsnakeball.androidimpl;

import android.graphics.Bitmap;

import com.ldm.futsnakeball.Pixmap;
import com.ldm.futsnakeball.Graficos;

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graficos.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graficos.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graficos.PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}

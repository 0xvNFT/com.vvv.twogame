package com.vvv.twogame.gametwo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Hole extends GameObject {
    private final Bitmap holeBitmap;

    public Hole(int x, int y, Bitmap holeBitmap, int holeWidth, int holeHeight) {
        super(x, y);
        this.holeBitmap = Bitmap.createScaledBitmap(holeBitmap, holeWidth, holeHeight, false);
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(holeBitmap, x, y, null);
    }

    @Override
    public boolean contains(float touchX, float touchY) {
        return false;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
    }

    public int getHeight() {
        return holeBitmap.getHeight();
    }

    public int getWidth() {
        return holeBitmap.getWidth();
    }
}

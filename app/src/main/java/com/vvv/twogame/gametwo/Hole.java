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

    public void update() {
    }
}

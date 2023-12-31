package com.vvv.twogame.gametwo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class HoleFront extends GameObject {
    private final Bitmap holeFrontBitmap;

    public HoleFront(int x, int y, Bitmap holeFrontBitmap, int holeWidth, int holeHeight) {
        super(x, y);
        this.holeFrontBitmap = Bitmap.createScaledBitmap(holeFrontBitmap, holeWidth, holeHeight, false);
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(holeFrontBitmap, x, y, null);
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

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    public void update() {
    }
}

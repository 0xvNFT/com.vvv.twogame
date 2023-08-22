package com.vvv.twogame.gametwo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Hammer extends GameObject {
    private final Bitmap hammerBitmap;
    private int hammerPosX;
    private int hammerPosY;
    private boolean isVisible;

    public Hammer(Bitmap hammerBitmap, int hammerWidth, int hammerHeight) {
        super(0, 0);
        this.hammerBitmap = Bitmap.createScaledBitmap(hammerBitmap, hammerWidth, hammerHeight, false);
        isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void show(int touchX, int touchY) {
        int hammerY = touchY - hammerBitmap.getHeight();

        hammerPosX = touchX;
        hammerPosY = hammerY;
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public void draw(Canvas canvas) {
        if (isVisible) {
            canvas.drawBitmap(hammerBitmap, hammerPosX, hammerPosY, null);
        }
    }

    @Override
    public boolean contains(float touchX, float touchY) {
        return false;
    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}

package com.vvv.twogame.gametwo;

import static com.vvv.twogame.gametwo.Constants.MOLE_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.MOLE_WIDTH;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Mole extends GameObject {
    private final Bitmap moleBitmap;
    private boolean isVisible;

    public Mole(int x, int y, Bitmap moleBitmap, int moleWidth, int moleHeight) {
        super(x, y);
        this.moleBitmap = Bitmap.createScaledBitmap(moleBitmap, moleWidth, moleHeight, false);
        isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public void whack() {
        isVisible = false;
    }

    public void draw(Canvas canvas) {
        if (isVisible) {
            canvas.drawBitmap(moleBitmap, x, y, null);
        }
    }

    public void update() {
    }

    public boolean contains(float touchX, float touchY) {
        int left = x;
        int right = x + MOLE_WIDTH;
        int top = y;
        int bottom = y + MOLE_HEIGHT;

        return touchX >= left && touchX <= right && touchY >= top && touchY <= bottom;
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
}

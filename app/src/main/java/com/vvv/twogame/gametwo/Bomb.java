package com.vvv.twogame.gametwo;

import static com.vvv.twogame.gametwo.Constants.BOMB_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.BOMB_WIDTH;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bomb extends GameObject {
    private final Bitmap bombBitmap;
    private boolean isVisible;

    public Bomb(int x, int y, Bitmap bombBitmap, int bombWidth, int bombHeight) {
        super(x, y);
        this.bombBitmap = Bitmap.createScaledBitmap(bombBitmap, bombWidth, bombHeight, false);
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

    public void detonate() {
        isVisible = false;
        // Perform bomb detonation logic here
    }

    public void draw(Canvas canvas) {
        if (isVisible) {
            canvas.drawBitmap(bombBitmap, x, y, null);
        }
    }

    public boolean contains(float touchX, float touchY) {
        // Calculate the bounds of the bomb for touch detection
        int left = x;
        int right = x + BOMB_WIDTH;
        int top = y;
        int bottom = y + BOMB_HEIGHT;

        // Check if the touch coordinates are within the bounds of the bomb
        return touchX >= left && touchX <= right && touchY >= top && touchY <= bottom;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return bombBitmap.getWidth();
    }

    public int getHeight() {
        return bombBitmap.getHeight();
    }
}


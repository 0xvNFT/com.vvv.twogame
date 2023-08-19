package com.vvv.twogame.gametwo;

import android.graphics.Canvas;

public class Mole extends GameObject {
    private boolean isVisible;

    public Mole(int x, int y) {
        super(x, y);
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

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if (isVisible) {

        }
    }
}

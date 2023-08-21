package com.vvv.twogame.gametwo;

import android.graphics.Canvas;

public abstract class GameObject {
    protected int x, y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {

    }

    public void draw(Canvas canvas) {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

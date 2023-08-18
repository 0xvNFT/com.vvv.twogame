package com.vvv.twogame.gameone;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class GameObject {
    protected int x, y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void draw(Canvas canvas);

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

    public abstract boolean checkCollision(GameObject other);

    public abstract Rect getBoundingBox();
}


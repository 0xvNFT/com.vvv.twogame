package com.vvv.twogame.gameone;

import android.graphics.Canvas;

public class Enemy extends GameObject implements Collidable {
    public Enemy(int x, int y) {
        super(x, y);
    }
    // ...

    @Override
    public boolean checkCollision(GameObject other) {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}

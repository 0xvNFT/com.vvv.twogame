package com.vvv.twogame.gameone;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Projectile extends GameObject implements Collidable {
    private final Bitmap[] projectileImages;
    private Bitmap currentImage;

    private int speed;

    public Projectile(Bitmap[] projectileImages, int x, int y, int speed, int chosenIndex) {
        super(x, y);
        this.projectileImages = projectileImages;
        this.speed = speed;
        this.currentImage = projectileImages[chosenIndex];
    }

    public Bitmap getCurrentImage() {
        return currentImage;
    }

    @Override
    public void update() {
        y -= speed;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, x, y, null);
    }

    @Override
    public boolean checkCollision(GameObject other) {
        return false;
    }

    public void fire(int startX, int startY, int speed, int chosenIndex) {
        this.x = startX;
        this.y = startY;
        this.speed = speed;
        this.currentImage = projectileImages[chosenIndex];
    }
}

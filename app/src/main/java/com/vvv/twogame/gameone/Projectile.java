package com.vvv.twogame.gameone;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Projectile extends GameObject implements Collidable {
    private final Bitmap[] projectileImages;
    private final Bitmap currentImage;

    private final int speed;

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

    @Override
    public Rect getBoundingBox() {
        return new Rect(x, y, x + currentImage.getWidth(), y + currentImage.getHeight());
    }
}

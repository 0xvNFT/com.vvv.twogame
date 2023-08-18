package com.vvv.twogame.gameone;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Player extends GameObject implements Collidable {
    private final Bitmap currentImage;
    private int health;

    public Player(Bitmap[] playerImages, int screenWidth, int screenHeight, int initialHealth) {
        super((screenWidth - playerImages[0].getWidth()) / 2, screenHeight - playerImages[0].getHeight());
        this.currentImage = playerImages[new Random().nextInt(playerImages.length)];
        this.x = (screenWidth - currentImage.getWidth()) / 2;
        this.y = screenHeight - currentImage.getHeight();
        this.health = initialHealth;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public Bitmap getCurrentImage() {
        return currentImage;
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, x, y, null);
        // Draw the player's health
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        canvas.drawText("Health: " + health, x, y - 20, paint);
    }


    @Override
    public boolean checkCollision(GameObject other) {
        if (other instanceof Enemy) {
            Enemy enemy = (Enemy) other;
            //decreaseHealth(1);
            return Rect.intersects(getBoundingBox(), enemy.getBoundingBox());
        }
        return false;
    }

    public Rect getBoundingBox() {
        return new Rect(x, y, x + currentImage.getWidth(), y + currentImage.getHeight());
    }
}
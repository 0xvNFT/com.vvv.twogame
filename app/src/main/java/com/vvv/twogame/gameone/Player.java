package com.vvv.twogame.gameone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.vvv.twogame.R;

import java.util.Random;

public class Player extends GameObject implements Collidable {
    private final Bitmap currentImage;
    private int health;
    private final Context context; // Add this member variable

    public Player(Context context, Bitmap[] playerImages, int screenWidth, int screenHeight, int initialHealth) {
        super((screenWidth - playerImages[0].getWidth()) / 2, screenHeight - playerImages[0].getHeight());
        this.context = context;
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

        int heartWidth = 35;
        int heartHeight = 35;
        int heartSpacing = 1;

        int totalHeartsWidth = (health * heartWidth) + ((health - 1) * heartSpacing);
        int initialX = x + (currentImage.getWidth() - totalHeartsWidth) / 2;

        Drawable heartDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.player_heart, null);

        for (int i = 0; i < health; i++) {
            if (heartDrawable != null) {
                int heartLeft = initialX + i * (heartWidth + heartSpacing);
                int heartTop = y + currentImage.getHeight() + heartSpacing;
                int heartRight = heartLeft + heartWidth;
                int heartBottom = heartTop + heartHeight;

                heartDrawable.setBounds(heartLeft, heartTop, heartRight, heartBottom);
                heartDrawable.draw(canvas);
            }
        }
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
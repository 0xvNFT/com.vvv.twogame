package com.vvv.twogame.gameone;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Player extends GameObject implements Collidable {
    private final Bitmap currentImage;


    public Player(Bitmap[] playerImages, int screenWidth, int screenHeight) {
        super((screenWidth - playerImages[0].getWidth()) / 2, screenHeight - playerImages[0].getHeight());
        this.currentImage = playerImages[new Random().nextInt(playerImages.length)];
        this.x = (screenWidth - currentImage.getWidth()) / 2;
        this.y = screenHeight - currentImage.getHeight();

    }

    public Bitmap getCurrentImage() {
        return currentImage;
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, x, y, null);
    }

    @Override
    public boolean checkCollision(GameObject other) {
        return false;
    }

}
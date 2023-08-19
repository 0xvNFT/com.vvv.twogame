package com.vvv.twogame.gameone;

import static com.vvv.twogame.gameone.Constants.HEART_HEIGHT;
import static com.vvv.twogame.gameone.Constants.HEART_SPACING;
import static com.vvv.twogame.gameone.Constants.HEART_WIDTH;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.vvv.twogame.R;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends GameObject implements Collidable {
    private final Bitmap currentImage;
    private final Context context;
    private final HealthManager healthManager;
    private final ScoreManager scoreManager;
    private final GameView gameView;

    public Player(Context context, Bitmap[] playerImages, int screenWidth, int screenHeight, int initialHealth, int maxHealth, GameView gameView) {
        super((screenWidth - playerImages[0].getWidth()) / 2, screenHeight - playerImages[0].getHeight());
        this.context = context;
        this.currentImage = playerImages[new Random().nextInt(playerImages.length)];
        this.x = (screenWidth - currentImage.getWidth()) / 2;
        this.y = screenHeight - currentImage.getHeight();
        this.healthManager = new HealthManager(initialHealth, maxHealth);
        this.scoreManager = new ScoreManager();
        this.gameView = gameView;
    }

    public void decreaseHealth(int amount) {
        if (!healthManager.isBlinking()) {
            healthManager.decreaseHealth(amount);

            if (healthManager.getCurrentHealth() > 0) {
                healthManager.startBlinking();
            }
        }
    }

    public Bitmap getCurrentImage() {
        return currentImage;
    }

    public void update() {
        //healthManager.isBlinking();
    }

    public void increaseScore(int amount) {
        gameView.getScoreManager().increaseScore(amount);
    }

    public void draw(Canvas canvas) {
        if (!healthManager.isBlinking() || (System.currentTimeMillis() - healthManager.getBlinkStartTime()) % 400 < 200) {
            canvas.drawBitmap(currentImage, x, y, null);
        }
        int heartWidth = HEART_WIDTH;
        AtomicInteger heartHeight = new AtomicInteger(HEART_HEIGHT);
        int heartSpacing = HEART_SPACING;

        int totalHeartsWidth = (healthManager.getCurrentHealth() * heartWidth) + ((healthManager.getCurrentHealth() - 1) * heartSpacing);
        int initialX = x + (currentImage.getWidth() - totalHeartsWidth) / 2;


        Drawable heartDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.player_heart, null);

        for (int i = 0; i < healthManager.getCurrentHealth(); i++) {
            if (heartDrawable != null) {
                int heartLeft = initialX + i * (heartWidth + heartSpacing);
                int heartTop = y + currentImage.getHeight() + heartSpacing;
                int heartRight = heartLeft + heartWidth;
                int heartBottom = heartTop + heartHeight.get();

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

    public int getScore() {
        return ScoreManager.getScore();
    }

    public int getHealth() {
        return healthManager.getCurrentHealth();
    }
}
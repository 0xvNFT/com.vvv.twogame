package com.vvv.twogame.gameone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.vvv.twogame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameView extends View {
    private final int[] playerImageResources = {
            R.drawable.player_image1,
            R.drawable.player_image2,
            R.drawable.player_image3,
            R.drawable.player_image4,
            R.drawable.player_image5,
            R.drawable.player_image6,
            R.drawable.player_image7
    };
    private final int[] projectileImageResources = {
            R.drawable.projectile1,
            R.drawable.projectile2,
            R.drawable.projectile3,
            R.drawable.projectile4,
            R.drawable.projectile5,
            R.drawable.projectile6
    };
    private final int PLAYER_IMAGE_WIDTH = 100;
    private final int PLAYER_IMAGE_HEIGHT = 100;
    private final int PROJECTILE_IMAGE_WIDTH = 80;
    private final int PROJECTILE_IMAGE_HEIGHT = 100;
    public Projectile projectiles;
    private final Player player;
    private final Bitmap[] projectileImages;
    private final int chosenProjectileIndex;
    private final Handler firingHandler = new Handler();
    private final Runnable firingRunnable;
    private final List<Projectile> activeProjectiles = new ArrayList<>();


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Bitmap[] playerImages = new Bitmap[7];
        for (int i = 0; i < 7; i++) {
            Bitmap originalPlayerImage = BitmapFactory.decodeResource(getResources(), playerImageResources[i]);
            playerImages[i] = Bitmap.createScaledBitmap(originalPlayerImage, PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT, false);
            originalPlayerImage.recycle();
        }
        projectileImages = new Bitmap[6];
        for (int i = 0; i < 6; i++) {
            Bitmap originalProjectileImage = BitmapFactory.decodeResource(getResources(), projectileImageResources[i]);
            projectileImages[i] = Bitmap.createScaledBitmap(originalProjectileImage, PROJECTILE_IMAGE_WIDTH, PROJECTILE_IMAGE_HEIGHT, false);
            originalProjectileImage.recycle();
        }

        player = new Player(playerImages, screenWidth, screenHeight);

        chosenProjectileIndex = new Random().nextInt(projectileImages.length);
        projectiles = new Projectile(projectileImages, screenWidth, screenHeight, 0, chosenProjectileIndex);

        firingRunnable = new Runnable() {
            @Override
            public void run() {
                fireProjectile();
                firingHandler.postDelayed(this, 300);
                invalidate();
            }
        };
        firingHandler.postDelayed(firingRunnable, 300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player.update();
        player.draw(canvas);
        for (Projectile projectile : activeProjectiles) {
            projectile.update();
            projectile.draw(canvas);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();


        float rawX = event.getRawX();
        float rawY = event.getRawY();

        int[] location = new int[2];
        getLocationOnScreen(location);
        float x = rawX - location[0];
        float y = rawY - location[1];
        //Log.d("TouchTest", "Touch event: " + action + " X: " + x + " Y: " + y);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //resetFiringTimer();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                player.setX((int) x - player.getCurrentImage().getWidth() / 2);
                player.setY((int) y - player.getCurrentImage().getHeight() / 2);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void fireProjectile() {
        int playerX = player.getX() + player.getCurrentImage().getWidth() / 10;
        int playerY = player.getY();
        int speed = 20;
        Projectile newProjectile = new Projectile(projectileImages, playerX, playerY, speed, chosenProjectileIndex);
        activeProjectiles.add(newProjectile);
    }

    private void resetFiringTimer() {
        firingHandler.removeCallbacks(firingRunnable);
        firingHandler.postDelayed(firingRunnable, 100);
    }
}

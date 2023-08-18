package com.vvv.twogame.gameone;

import static com.vvv.twogame.gameone.Constants.ENEMY_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.ENEMY_IMAGE_WIDTH;
import static com.vvv.twogame.gameone.Constants.ENEMY_SCREEN_HEIGHT;
import static com.vvv.twogame.gameone.Constants.ENEMY_SCREEN_WIDTH;
import static com.vvv.twogame.gameone.Constants.PLAYER_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.PLAYER_IMAGE_WIDTH;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_IMAGE_WIDTH;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.vvv.twogame.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class GameView extends View {
    public Projectile projectiles;
    private final Player player;
    private final Bitmap[] projectileImages;
    private final int chosenProjectileIndex;
    private final Handler firingHandler = new Handler();
    private final Runnable enemySpawningRunnable;
    private final Runnable projectileFiringRunnable;
    private final List<Projectile> activeProjectiles = new ArrayList<>();
    private final List<Enemy> activeEnemies = new ArrayList<>();
    private final Bitmap[] enemyImages;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int[] playerImageResources = {
                R.drawable.player_image1,
                R.drawable.player_image2,
                R.drawable.player_image3,
                R.drawable.player_image4,
                R.drawable.player_image5,
                R.drawable.player_image6,
                R.drawable.player_image7
        };
        Bitmap[] playerImages = new Bitmap[playerImageResources.length];
        for (int i = 0; i < 7; i++) {
            Bitmap originalPlayerImage = BitmapFactory.decodeResource(getResources(), playerImageResources[i]);
            playerImages[i] = Bitmap.createScaledBitmap(originalPlayerImage, PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT, false);
            originalPlayerImage.recycle();
        }
        int[] projectileImageResources = {
                R.drawable.projectile1,
                R.drawable.projectile2,
                R.drawable.projectile3,
                R.drawable.projectile4,
                R.drawable.projectile5,
                R.drawable.projectile6
        };
        projectileImages = new Bitmap[projectileImageResources.length];
        for (int i = 0; i < 6; i++) {
            Bitmap originalProjectileImage = BitmapFactory.decodeResource(getResources(), projectileImageResources[i]);
            projectileImages[i] = Bitmap.createScaledBitmap(originalProjectileImage, PROJECTILE_IMAGE_WIDTH, PROJECTILE_IMAGE_HEIGHT, false);
            originalProjectileImage.recycle();
        }
        int[] enemyImageResources = {
                R.drawable.enemy1,
                R.drawable.enemy2,
                R.drawable.enemy3,
                R.drawable.enemy4,
                R.drawable.enemy5,
                R.drawable.enemy6,
                R.drawable.enemy7
        };
        enemyImages = new Bitmap[enemyImageResources.length];
        for (int i = 0; i < 7; i++) {
            Bitmap originalEnemyImage = BitmapFactory.decodeResource(getResources(), enemyImageResources[i]);
            enemyImages[i] = Bitmap.createScaledBitmap(originalEnemyImage, ENEMY_IMAGE_WIDTH, ENEMY_IMAGE_HEIGHT, false);
            originalEnemyImage.recycle();
        }

        player = new Player(playerImages, screenWidth, screenHeight, 3);

        chosenProjectileIndex = new Random().nextInt(projectileImages.length);
        projectiles = new Projectile(projectileImages, screenWidth, screenHeight, 0, chosenProjectileIndex);

        projectileFiringRunnable = new Runnable() {
            @Override
            public void run() {
                fireProjectile();
                firingHandler.postDelayed(this, 400);
            }
        };
        firingHandler.postDelayed(projectileFiringRunnable, 400);

        enemySpawningRunnable = new Runnable() {
            @Override
            public void run() {
                spawnEnemy();
                firingHandler.postDelayed(this, 300);
            }
        };
        firingHandler.postDelayed(enemySpawningRunnable, 300);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        player.update();
        player.draw(canvas);

        Iterator<Projectile> projectileIterator = activeProjectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            projectile.update();
            projectile.draw(canvas);

            if (projectile.getY() < 0) {
                projectileIterator.remove();
            }
        }
        Iterator<Enemy> enemyIterator = activeEnemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.update();
            enemy.draw(canvas);

            if (enemy.getY() > getHeight()) {
                enemyIterator.remove();
            }
        }
        checkCollisions();

        invalidate();
    }

    private void checkCollisions() {
        Iterator<Projectile> projectileIterator = activeProjectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();

            Iterator<Enemy> enemyIterator = activeEnemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();

                if (enemy.checkCollision(projectile)) {
                    enemyIterator.remove();
                    projectileIterator.remove();
                    break;
                }
            }
            if (player.checkCollision(projectile)) {
                player.decreaseHealth(1);
                projectileIterator.remove();
            }
        }
        Iterator<Enemy> enemyIterator = activeEnemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();

            if (player.checkCollision(enemy)) {
                player.decreaseHealth(1);
                enemyIterator.remove();
            }
        }
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
                //resetEnemySpawningTimer
                //spawnEnemy();
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
        int playerX = player.getX() + player.getCurrentImage().getWidth() / 4;
        int playerY = player.getY();
        int speed = 20;
        Projectile newProjectile = new Projectile(projectileImages, playerX, playerY, speed, chosenProjectileIndex);
        activeProjectiles.add(newProjectile);
    }

    private void resetFiringTimer() {
        firingHandler.removeCallbacks(projectileFiringRunnable);
        firingHandler.postDelayed(projectileFiringRunnable, 400);
    }

    private void resetEnemySpawningTimer() {
        firingHandler.removeCallbacks(enemySpawningRunnable);
        firingHandler.postDelayed(enemySpawningRunnable, 1000);
    }

    private void spawnEnemy() {
        int screenWidth = getWidth();

        int enemyX = new Random().nextInt(screenWidth - ENEMY_SCREEN_WIDTH);
        int enemyY = -ENEMY_SCREEN_HEIGHT;
        int minSpeed = 5;
        int maxSpeed = 15;
        int speed = new Random().nextInt(maxSpeed - minSpeed + 1) + minSpeed;

        int chosenEnemyIndex = new Random().nextInt(enemyImages.length);
        Enemy newEnemy = new Enemy(enemyImages[chosenEnemyIndex], enemyX, enemyY, speed);
        activeEnemies.add(newEnemy);
    }
}

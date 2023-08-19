package com.vvv.twogame.gameone;

import static com.vvv.twogame.gameone.Constants.ENEMY_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.ENEMY_IMAGE_WIDTH;
import static com.vvv.twogame.gameone.Constants.ENEMY_SCREEN_HEIGHT;
import static com.vvv.twogame.gameone.Constants.ENEMY_SCREEN_WIDTH;
import static com.vvv.twogame.gameone.Constants.ENEMY_SPAWN_DELAY;
import static com.vvv.twogame.gameone.Constants.ENEMY_SPEED_MAX;
import static com.vvv.twogame.gameone.Constants.ENEMY_SPEED_MIN;
import static com.vvv.twogame.gameone.Constants.HEART_COUNT;
import static com.vvv.twogame.gameone.Constants.MAX_HEARTS;
import static com.vvv.twogame.gameone.Constants.PLAYER_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.PLAYER_IMAGE_WIDTH;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_FIRING_DELAY;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_IMAGE_WIDTH;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_SPEED;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.vvv.twogame.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class GameView extends View {
    public final Paint scorePaint;
    private final Player player;
    private final Bitmap[] projectileImages;
    private final int chosenProjectileIndex;
    private final Handler firingHandler = new Handler();
    private final Runnable enemySpawningRunnable;
    private final Runnable projectileFiringRunnable;
    private final List<Projectile> activeProjectiles = new ArrayList<>();
    private final List<Enemy> activeEnemies = new ArrayList<>();
    private final Bitmap[] enemyImages;
    private final ScoreManager scoreManager;
    private final TimerManager timerManager;
    public Projectile projectiles;
    private boolean isGameActive = false;
    private boolean isRulesDialogShown = false;
    private boolean fireProjectiles = false;
    private boolean canSpawnEnemies = false;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        showRulesDialog();
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
        //remove context if you want to use number as health -jayson
        player = new Player(context, playerImages, screenWidth, screenHeight, HEART_COUNT, MAX_HEARTS, this);

        chosenProjectileIndex = new Random().nextInt(projectileImages.length);
        projectiles = new Projectile(projectileImages, screenWidth, screenHeight, PROJECTILE_SPEED, chosenProjectileIndex);

        projectileFiringRunnable = new Runnable() {
            @Override
            public void run() {
                fireProjectile();
                firingHandler.postDelayed(this, PROJECTILE_FIRING_DELAY);
            }
        };
        firingHandler.postDelayed(projectileFiringRunnable, PROJECTILE_FIRING_DELAY);

        enemySpawningRunnable = new Runnable() {
            @Override
            public void run() {
                spawnEnemy();
                firingHandler.postDelayed(this, ENEMY_SPAWN_DELAY);
            }
        };
        firingHandler.postDelayed(enemySpawningRunnable, ENEMY_SPAWN_DELAY);

        scoreManager = new ScoreManager();
        scorePaint = new Paint();
        timerManager = new TimerManager();
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public TimerManager getTimerManager() {
        return timerManager;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (isRulesDialogShown && isGameActive) {
            scoreManager.drawScore(canvas);
            timerManager.update();
            timerManager.draw(canvas);
            if (timerManager.isTimeUp()) {
                showLevelCompleteDialog();
                timerManager.resetTimer();
                isGameActive = false;

            }

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
        }
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
                    player.increaseScore(1);
                    break;
                }
            }
            if (player.checkCollision(projectile)) {
                player.decreaseHealth(1);
                projectileIterator.remove();
            }
            if (player.getHealth() <= 0) {
                showGameOverDialog(player.getScore());
                isGameActive = false;
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
        if (!fireProjectiles) {
            return;
        }
        int playerX = player.getX() + player.getCurrentImage().getWidth() / 4;
        int playerY = player.getY();
        AtomicInteger speed = new AtomicInteger(PROJECTILE_SPEED);
        Projectile newProjectile = new Projectile(projectileImages, playerX, playerY, speed.get(), chosenProjectileIndex);
        activeProjectiles.add(newProjectile);
    }

    private void resetFiringTimer() {
        firingHandler.removeCallbacks(projectileFiringRunnable);
        firingHandler.postDelayed(projectileFiringRunnable, PROJECTILE_FIRING_DELAY);
    }

    private void resetEnemySpawningTimer() {
        firingHandler.removeCallbacks(enemySpawningRunnable);
        firingHandler.postDelayed(enemySpawningRunnable, ENEMY_SPAWN_DELAY);
    }

    private void spawnEnemy() {
        if (!canSpawnEnemies) {
            return;
        }
        int screenWidth = getWidth();

        int enemyX = new Random().nextInt(screenWidth - ENEMY_SCREEN_WIDTH);
        int enemyY = -ENEMY_SCREEN_HEIGHT;
        int minSpeed = ENEMY_SPEED_MIN;
        AtomicInteger maxSpeed = new AtomicInteger(ENEMY_SPEED_MAX);
        int speed = new Random().nextInt(maxSpeed.get() - minSpeed + 1) + minSpeed;

        int chosenEnemyIndex = new Random().nextInt(enemyImages.length);
        Enemy newEnemy = new Enemy(enemyImages[chosenEnemyIndex], enemyX, enemyY, speed);
        activeEnemies.add(newEnemy);
    }

    private void showRulesDialog() {
        DialogInterface.OnClickListener onProceedClickListener = (dialog, which) -> {
            isRulesDialogShown = true;
            isGameActive = true;
            fireProjectiles = true;
            canSpawnEnemies = true;
            Log.d("GameView", "Start Game button clicked. Rules shown: " + true + ", Game active: " + true);

        };
        ShowRulesDialog dialog = new ShowRulesDialog(getContext(), onProceedClickListener);
        dialog.show();
    }

    private void showLevelCompleteDialog() {

        DialogInterface.OnClickListener onProceedClickListener = (dialog, which) -> {
            ((Activity) getContext()).finish();

            Intent intent = new Intent(getContext(), SpaceShooterActivity.class);
            getContext().startActivity(intent);
            isGameActive = true;
        };

        LevelCompleteDialog dialog = new LevelCompleteDialog(getContext(), ScoreManager.getScore(), onProceedClickListener);
        dialog.show();
    }

    private void showGameOverDialog(int finalScore) {
        DialogInterface.OnClickListener onRestartClickListener = (dialog, which) -> resetGame();
        GameOverDialog dialog = new GameOverDialog(getContext(), onRestartClickListener);
        dialog.show(finalScore);
    }

    private void resetGame() {
        ((Activity) getContext()).finish();
        Intent intent = new Intent(getContext(), SpaceShooterActivity.class);
        getContext().startActivity(intent);
    }
}

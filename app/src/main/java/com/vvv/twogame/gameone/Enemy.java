package com.vvv.twogame.gameone;

import static com.vvv.twogame.gameone.Constants.ENEMY_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.ENEMY_IMAGE_WIDTH;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_IMAGE_HEIGHT;
import static com.vvv.twogame.gameone.Constants.PROJECTILE_IMAGE_WIDTH;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy extends GameObject implements Collidable {
    private final Bitmap enemyImage;
    private final int speed;
    private final Rect enemyRect = new Rect();
    private final Rect projectileRect = new Rect();

    public Enemy(Bitmap enemyImage, int x, int y, int speed) {
        super(x, y);
        this.enemyImage = enemyImage;
        this.speed = speed;
    }

    @Override
    public boolean checkCollision(GameObject other) {
        if (other instanceof Projectile) {
            Projectile projectile = (Projectile) other;
            enemyRect.set(x, y, x + ENEMY_IMAGE_WIDTH, y + ENEMY_IMAGE_HEIGHT);
            projectileRect.set(projectile.getX(), projectile.getY(),
                    projectile.getX() + PROJECTILE_IMAGE_WIDTH, projectile.getY() + PROJECTILE_IMAGE_HEIGHT);
            return Rect.intersects(enemyRect, projectileRect);
        }
        return false;
    }

    @Override
    public void update() {
        y += speed;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(enemyImage, x, y, null);

    }
}

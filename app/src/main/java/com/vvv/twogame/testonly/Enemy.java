//package com.vvv.twogame.game1;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.RectF;
//
//import com.vvv.twogame.R;
//
//public class Enemy {
//
//    private float x, y; // Current position of the enemy
//    private float speed; // Speed of the enemy
//    private RectF hitbox; // Collision hitbox for the enemy
//    private Bitmap enemyBitmap; // Image of the enemy
//    private final int enemyWidth = 100; // Width of the enemy
//    private final int enemyHeight = 100; // Height of the enemy
//
//    public Enemy(Context context, float startX, float startY) {
//        x = startX;
//        y = startY;
//        speed = 3.0f; // Set initial speed
//        hitbox = new RectF(x, y, x + enemyWidth, y + enemyHeight);
//
//        // Load enemy image from resources
//        enemyBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy1);
//    }
//
//    public void update() {
//        // Update enemy's position and hitbox based on movement pattern
//        // Implement movement logic here (e.g., linear movement, zigzag movement)
//    }
//
//    public void draw(Canvas canvas) {
//        canvas.drawBitmap(enemyBitmap, x, y, null);
//    }
//
//    public RectF getHitbox() {
//        return hitbox;
//    }
//}

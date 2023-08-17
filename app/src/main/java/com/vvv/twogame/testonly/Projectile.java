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
//import java.util.ArrayList;
//import java.util.List;
//
//public class Projectile {
//
//    private float x, y; // Current position of the projectile
//    private float speed; // Speed of the projectile
//    private RectF hitbox; // Collision hitbox for the projectile
//    private Bitmap projectileBitmap; // Image of the projectile
//    private final int projectileWidth = 50; // Width of the projectile
//    private final int projectileHeight = 100; // Height of the projectile
//    private List<Projectile> projectiles;
//    private boolean needsRemoval = false; // Flag to indicate if the projectile needs to be removed
//    private final int screenWidth;
//    private final int screenHeight;
//    private long lastFiredTime; // Time when the last projectile was fired
//    private long fireIntervalMillis = 2000; // Firing interval of 2 seconds (2000 milliseconds)
//    private Context context; // Add a member to hold the context
//
//    public Projectile(Context context, float startX, float startY) {
//        this.context = context; // Initialize the context
//
//        projectiles = new ArrayList<>();
//        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
//        x = startX;
//        y = startY;
//        speed = 10.0f; // Set initial speed
//        hitbox = new RectF(x, y, x + projectileWidth, y + projectileHeight);
//
//        // Load projectile image from resources
//        projectileBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile1);
//        lastFiredTime = System.currentTimeMillis(); // Initialize last fired time
//
//    }
//
//    public void update() {
//        // Check if the firing cooldown has elapsed
//        long currentTime = System.currentTimeMillis();
//        if (currentTime - lastFiredTime >= fireIntervalMillis) {
//            shoot();
//            lastFiredTime = currentTime; // Update the last fired time
//        }
//
//        y -= speed;
//        hitbox.set(x, y, x + projectileWidth, y + projectileHeight);
//        if (y < 0 || y > screenHeight) {
//            needsRemoval = true;
//        }
//    }
//    private void shoot() {
//        // Create a new projectile at the current position
//        Projectile newProjectile = new Projectile(context,x, y);
//        projectiles.add(newProjectile); // Assuming you have a list to store projectiles
//    }
//
//    public void draw(Canvas canvas) {
//        Bitmap scaledBitmap = Bitmap.createScaledBitmap(projectileBitmap, projectileWidth, projectileHeight, false);
//        canvas.drawBitmap(scaledBitmap, x, y, null);
//    }
//    public RectF getHitbox() {
//        return hitbox;
//    }
//
//    public boolean isOutOfBounds() {
//        return x < 0 || x > screenWidth || y < 0 || y > screenHeight;
//    }
//
//    public boolean needsRemoval() {
//        return needsRemoval;
//    }
//}

//package com.vvv.twogame.game1;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.RectF;
//import android.view.Display;
//import android.view.WindowManager;
//
//import com.vvv.twogame.R;
//
//public class PlayerSpaceship {
//
//    private float x, y; // Current position of the player spaceship
//    private float speed; // Speed of the player spaceship
//    private RectF hitbox; // Collision hitbox for the player spaceship
//    private Bitmap spaceshipBitmap; // Image of the player spaceship
//    private final int spaceshipWidth = 100; // Width of the player spaceship
//    private final int spaceshipHeight = 100; // Height of the player spaceship
//
//    public PlayerSpaceship(Context context) {
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        int screenWidth = display.getWidth();
//        int screenHeight = display.getHeight();
//        x = (float) (screenWidth - spaceshipWidth) / 2;
//        y = screenHeight - spaceshipHeight;
//
//        speed = 1.0f; // Set initial speed
//        hitbox = new RectF(x, y, x + spaceshipWidth, y + spaceshipHeight);
//
//        // Load spaceship image from resources
//        spaceshipBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship_1);
//    }
//
//    public void update(int touchX, int touchY) {
//        // Calculate the distance and angle as before
//        float deltaX = touchX - x;
//        float deltaY = touchY - y;
//        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
//
//        // Adjust the sensitivity factor to control responsiveness
//        float sensitivityFactor = 0.3f; // Adjust the factor as needed
//        float adjustedDistance = distance * sensitivityFactor;
//        // Calculate the angle between the spaceship and the touch position
//        double angle = Math.atan2(deltaY, deltaX);
//        // Calculate the new position based on the adjusted distance and angle
//        float newX = x + (float) (adjustedDistance * Math.cos(angle));
//        float newY = y + (float) (adjustedDistance * Math.sin(angle));
//
//        // Update the position and hitbox
//        x = newX;
//        y = newY;
//        hitbox.set(x, y, x + spaceshipWidth, y + spaceshipHeight);
//    }
//    public void draw(Canvas canvas) {
//        Bitmap scaledBitmap = Bitmap.createScaledBitmap(spaceshipBitmap, spaceshipWidth, spaceshipHeight, false);
//        canvas.drawBitmap(scaledBitmap, x, y, null);
//    }
//    public void shoot() {
//        // Create and shoot a projectile from the spaceship
//    }
//
//    public RectF getHitbox() {
//        return hitbox;
//    }
//    public float getX() {
//        return x;
//    }
//    public float getY() {
//        return y;
//    }
//
//    public void update() {
//    }
//}

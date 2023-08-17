//package com.vvv.twogame.game1;
//
//import android.graphics.Canvas;
//
//public class GameManager {
//    private SpaceShooterView gameView;
//    private boolean isGameRunning = false;
//
//    public GameManager(SpaceShooterView gameView) {
//        this.gameView = gameView;
//    }
//
//    public void startGame() {
//        isGameRunning = true;
//        // Initialize game objects, player spaceship, enemies, etc.
//    }
//
//    public void pauseGame() {
//        isGameRunning = false;
//    }
//
//    public void resumeGame() {
//        isGameRunning = true;
//    }
//
//    public void endGame() {
//        isGameRunning = false;
//        // Clean up resources and perform necessary actions for game over
//    }
//
//    public void updateGame(long elapsedTime) {
//        if (isGameRunning) {
//            // Update game logic here
//            // Update player spaceship, enemies, collision detection, etc.
//        }
//    }
//
//    public void drawGame(Canvas canvas) {
//        if (isGameRunning) {
//            // Draw game graphics here
//            // Draw player spaceship, enemies, projectiles, UI, etc.
//        }
//    }
//}
//

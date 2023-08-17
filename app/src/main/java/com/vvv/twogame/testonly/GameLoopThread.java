//package com.vvv.twogame.game1;
//
//import android.graphics.Canvas;
//import android.view.SurfaceHolder;
//
//public class GameLoopThread extends Thread {
//
//    private static final long FPS = 60; // Frames per second
//    private volatile boolean running = false;
//
//    private SpaceShooterView gameView;
//    private SurfaceHolder surfaceHolder; // SurfaceHolder for rendering
//
//    public GameLoopThread(SpaceShooterView gameView) {
//        this.gameView = gameView;
//        this.surfaceHolder = gameView.getHolder();
//    }
//
//    public void setRunning(boolean run) {
//        running = run;
//    }
//
//    @Override
//    public void run() {
//        long startTime;
//        long sleepTime;
//        long frameDuration = 1000 / FPS; // Duration of each frame in milliseconds
//
//        while (running) {
//            startTime = System.currentTimeMillis();
//
//            // Obtain a valid Canvas from the SurfaceHolder
//            Canvas canvas = surfaceHolder.lockCanvas();
//            if (canvas != null) {
//                gameView.update(); // Update game logic
//                gameView.draw(canvas); // Render frame
//                surfaceHolder.unlockCanvasAndPost(canvas);
//            }
//
//            sleepTime = frameDuration - (System.currentTimeMillis() - startTime);
//            if (sleepTime > 0) {
//                try {
//                    Thread.sleep(sleepTime);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}

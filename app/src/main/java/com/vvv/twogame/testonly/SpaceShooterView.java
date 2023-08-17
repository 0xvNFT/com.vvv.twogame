//package com.vvv.twogame.game1;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//import com.vvv.twogame.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class SpaceShooterView extends SurfaceView implements SurfaceHolder.Callback {
//
//    private GameLoopThread gameLoopThread;
//    private GameManager gameManager;
//    private CollisionManager collisionManager;
//    private ScoreManager scoreManager;
//    private PlayerSpaceship playerSpaceship;
//    private List<Enemy> enemies;
//    //private List<Projectile> projectiles;
//    private Bitmap backgroundBitmap;
//    private CopyOnWriteArrayList<Projectile> projectiles; // Use CopyOnWriteArrayList
//    private List<Projectile> projectilesToRemove;
//
//
//
//    public SpaceShooterView(Context context) {
//        super(context);
//        getHolder().addCallback(this);
//        initialize();
//    }
//    public SpaceShooterView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initialize();
//    }
//
//    private void initialize() {
//        // Load background image
//        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.galaxy_bg);
//        // Initialize managers
//        gameManager = new GameManager(this);
//        collisionManager = new CollisionManager();
//        scoreManager = new ScoreManager(getContext());
//
//
//
//        // Initialize game objects
//        playerSpaceship = new PlayerSpaceship(this.getContext());
//        enemies = new ArrayList<>();
//        projectiles = new CopyOnWriteArrayList<>();
//        projectilesToRemove = new ArrayList<>();
//
//        // Add enemies and projectiles as needed
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        gameLoopThread = new GameLoopThread(this);
//        gameLoopThread.setRunning(true);
//        gameLoopThread.start();
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//        // Handle surface changes if needed
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        boolean retry = true;
//        gameLoopThread.setRunning(false);
//        while (retry) {
//            try {
//                gameLoopThread.join();
//                retry = false;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void update() {
//        // Update game logic
//        playerSpaceship.update();
//        for (Enemy enemy : enemies) {
//            enemy.update();
//        }
//
//        for (Projectile projectile : projectiles) {
//            projectile.update();
//
//            // If the projectile is out of bounds, mark it for removal
//            if (projectile.isOutOfBounds()) {
//                projectilesToRemove.add(projectile);
//            }
//        }
//
//        // Remove projectiles that need removal
//        // Add projectiles to remove in the main list
//        projectiles.removeAll(projectilesToRemove);
//        projectilesToRemove.clear(); // Clear the list for the next frame
//
//
//        // Check collisions
//        CollisionManager.checkCollisions(playerSpaceship, enemies, projectiles);
//
//        // Update score
//        scoreManager.updateScore(10);
//
//        // Other game logic updates
//    }
//
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        // Draw background image first
//        canvas.drawBitmap(backgroundBitmap, 0, 0, null);
//
//        // Draw game objects on the canvas
//
//        playerSpaceship.draw(canvas);
//        for (Enemy enemy : enemies) {
//            enemy.draw(canvas);
//        }
//        List<Projectile> projectilesToRemove = new ArrayList<>();
//
//        for (Projectile projectile : projectiles) {
//            projectile.draw(canvas);
//            projectile.update();
//
//            // Check if the projectile is out of bounds or other conditions that require removal
//            if (projectile.isOutOfBounds() || projectile.needsRemoval()) {
//                projectilesToRemove.add(projectile);
//            }
//        }
//
//        // Remove projectiles that need to be removed
//        projectiles.removeAll(projectilesToRemove);
//
//
//        // Other drawing logic
//    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        int touchX = (int) event.getX();
//        int touchY = (int) event.getY();
//
//        playerSpaceship.update(touchX, touchY);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//
//            case MotionEvent.ACTION_MOVE:
//                Projectile projectile = new Projectile(getContext(), playerSpaceship.getX(), playerSpaceship.getY());
//                projectiles.add(projectile);
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//
//        }
//
//        return true;
//    }
//
//    public void pauseGame() {
//        if (gameLoopThread != null) { // Check if the gameLoopThread is not null
//            gameLoopThread.setRunning(false);
//        }
//    }
//
//    public void resumeGame() {
//        if (gameLoopThread != null) { // Check if the gameLoopThread is not null
//            gameLoopThread.setRunning(true);
//        }
//    }
//}

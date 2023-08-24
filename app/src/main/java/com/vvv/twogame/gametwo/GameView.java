package com.vvv.twogame.gametwo;

import static com.vvv.twogame.gametwo.Constants.BOMB_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.BOMB_WIDTH;
import static com.vvv.twogame.gametwo.Constants.COLUMN_SPACING;
import static com.vvv.twogame.gametwo.Constants.HAMMER_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.HAMMER_WIDTH;
import static com.vvv.twogame.gametwo.Constants.HOLE_FRONT_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.HOLE_FRONT_WIDTH;
import static com.vvv.twogame.gametwo.Constants.HOLE_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.HOLE_WIDTH;
import static com.vvv.twogame.gametwo.Constants.MAX_HEALTH;
import static com.vvv.twogame.gametwo.Constants.MOLE_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.MOLE_WIDTH;
import static com.vvv.twogame.gametwo.Constants.NUM_COLUMNS;
import static com.vvv.twogame.gametwo.Constants.NUM_ROWS;
import static com.vvv.twogame.gametwo.Constants.ROW_SPACING;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.vvv.twogame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends View implements QuitDialogListener {
    private final List<Hole> holes;
    private final List<HoleFront> holesFront;
    private final List<Mole> moles;
    private final List<Bomb> bombs;

    private final Handler handler = new Handler();
    private final Runnable moleRunnable;
    private final Runnable bombRunnable;
    private final Hammer hammer;
    private final Health health;
    private final ScoreManager scoreManager;
    private final TimerManager timerManager;
    private boolean isGameActive = false;
    private boolean isRulesDialogShown = false;
    private boolean isGamePaused = false;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        showRulesDialog();

        health = new Health(MAX_HEALTH);
        scoreManager = new ScoreManager();
        timerManager = new TimerManager();

        int holeWidth = HOLE_WIDTH;
        int holeHeight = HOLE_HEIGHT;
        int holeFrontWidth = HOLE_FRONT_WIDTH;
        int holeFrontHeight = HOLE_FRONT_HEIGHT;
        int moleWidth = MOLE_WIDTH;
        int moleHeight = MOLE_HEIGHT;
        int bombWidth = BOMB_WIDTH;
        int bombHeight = BOMB_HEIGHT;
        int columnSpacing = COLUMN_SPACING;
        int rowSpacing = ROW_SPACING;
        int hammerWidth = HAMMER_WIDTH;
        int hammerHeight = HAMMER_HEIGHT;

        int totalWidth = NUM_COLUMNS * holeWidth + (NUM_COLUMNS - 1) * columnSpacing;
        int startX = (screenWidth - totalWidth) / 2;

        Bitmap holeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hole_half);
        Bitmap moleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mole_alive);
        Bitmap holeFrontBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hole_front);
        Bitmap bombBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
        Bitmap hammerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hammer);
        hammer = new Hammer(hammerBitmap, hammerWidth, hammerHeight);

        holes = new ArrayList<>();
        moles = new ArrayList<>();
        holesFront = new ArrayList<>();
        bombs = new ArrayList<>();

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                //holes
                int holeX = startX + col * (holeWidth + columnSpacing);
                int holeY = screenHeight - (row + 1) * (holeHeight + rowSpacing);
                Hole hole = new Hole(holeX, holeY, holeBitmap, holeWidth, holeHeight);
                holes.add(hole);
                //moles
                int moleX = holeX + (holeWidth - moleWidth) / 2;
                int moleY = holeY + (holeHeight - moleHeight) / 2;
                Mole mole = new Mole(moleX, moleY, moleBitmap, moleWidth, moleHeight);
                moles.add(mole);
                //holesFront as mask
                int holeFrontX = holeX + (holeWidth - holeFrontWidth) / 2;
                int holeFrontY = moleY + (moleHeight - holeFrontHeight) + 5;
                HoleFront holeFront = new HoleFront(holeFrontX, holeFrontY, holeFrontBitmap, holeFrontWidth, holeFrontHeight);
                holesFront.add(holeFront);
                //bombs
                int bombX = holeX + (holeWidth - bombWidth) / 2;
                int bombY = holeY + (holeHeight - bombHeight) / 2;
                Bomb bomb = new Bomb(bombX, bombY, bombBitmap, bombWidth, bombHeight);
                bombs.add(bomb);
            }
        }
        moleRunnable = new Runnable() {
            @Override
            public void run() {
                int visibleMoleCount = 0;
                for (Mole mole : moles) {
                    if (mole.isVisible()) {
                        visibleMoleCount++;
                    }
                }

                if (visibleMoleCount < 3) {
                    List<Mole> hiddenMoles = new ArrayList<>();
                    for (Mole mole : moles) {
                        if (!mole.isVisible()) {
                            hiddenMoles.add(mole);
                        }
                    }

                    if (!hiddenMoles.isEmpty()) {
                        int randomIndex = new Random().nextInt(hiddenMoles.size());
                        Mole moleToShow = hiddenMoles.get(randomIndex);
                        moleToShow.show();

                        int randomInterval = new Random().nextInt(4) + 1;
                        int intervalMillis = randomInterval * 1500;

                        handler.postDelayed(() -> {
                            moleToShow.hide();
                            invalidate();
                            handler.postDelayed(this, intervalMillis);
                        }, intervalMillis);
                    }
                }

                invalidate();
                handler.postDelayed(this, 1500);

            }
        };
        handler.postDelayed(moleRunnable, 1500);
        bombRunnable = new Runnable() {
            @Override
            public void run() {
                int visibleBombCount = 0;
                for (Bomb bomb : bombs) {
                    if (bomb.isVisible()) {
                        visibleBombCount++;
                    }
                }

                if (visibleBombCount < 3) {
                    List<Bomb> hiddenBombs = new ArrayList<>();
                    for (Bomb bomb : bombs) {
                        if (!bomb.isVisible()) {
                            hiddenBombs.add(bomb);
                        }
                    }

                    if (!hiddenBombs.isEmpty()) {
                        int randomIndex = new Random().nextInt(hiddenBombs.size());
                        Bomb bombToShow = hiddenBombs.get(randomIndex);
                        bombToShow.show();

                        int randomInterval = new Random().nextInt(4) + 1;
                        int intervalMillis = randomInterval * 1500;

                        handler.postDelayed(() -> {
                            bombToShow.hide();
                            invalidate();
                            handler.postDelayed(this, intervalMillis);
                        }, intervalMillis);
                    }
                }

                invalidate();
                handler.postDelayed(this, 1500);

            }
        };
        handler.postDelayed(bombRunnable, 1500);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (isRulesDialogShown && isGameActive && !isGamePaused) {
            timerManager.update();
            timerManager.draw(canvas);
            if (timerManager.isTimeUp()) {
                showLevelCompleteDialog();
                timerManager.resetTimer();
                isGameActive = false;
            }
            for (int row = 0; row < Constants.NUM_ROWS; row++) {
                for (int col = 0; col < Constants.NUM_COLUMNS; col++) {

                    Hole hole = holes.get(row * Constants.NUM_COLUMNS + col);
                    hole.draw(canvas);

                    Mole mole = moles.get(row * Constants.NUM_COLUMNS + col);
                    mole.draw(canvas);

                    Bomb bomb = bombs.get(row * Constants.NUM_COLUMNS + col);
                    bomb.draw(canvas);

                    HoleFront holeFront = holesFront.get(row * Constants.NUM_COLUMNS + col);
                    holeFront.draw(canvas);

                    hammer.draw(canvas);
                    health.draw(canvas);
                    scoreManager.draw(canvas);
                }
            }
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();

            hammer.show((int) touchX, (int) touchY);
            Log.d("GameView", "Touch coordinates: x=" + touchX + ", y=" + touchY);

            for (Mole mole : moles) {
                if (mole.contains(touchX, touchY) && mole.isVisible()) {
                    mole.whack();
                    scoreManager.increaseScore();
                    invalidate();
                }
            }
            for (Bomb bomb : bombs) {
                if (bomb.contains(touchX, touchY) && bomb.isVisible()) {
                    bomb.detonate();
                    invalidate();
                    health.reduceHealth();
                    scoreManager.decreaseScore();
                    Log.d("GameView", "Health reduced due to bomb hit");
                }
            }
            if (health.getHealth() <= 0) {
                showGameOverDialog(ScoreManager.getScore());
                isGameActive = false;
            }

        } else if (action == MotionEvent.ACTION_UP) {
            hammer.hide();
            invalidate();
        }
        return true;
    }

    private void showRulesDialog() {
        DialogInterface.OnClickListener onProceedClickListener = (dialog, which) -> {
            isRulesDialogShown = true;
            isGameActive = true;
            Log.d("GameView", "Start Game button clicked. Rules shown: " + true + ", Game active: " + true);

        };
        com.vvv.twogame.gametwo.ShowRulesDialog dialog = new ShowRulesDialog(getContext(), onProceedClickListener);
        dialog.show();
    }

    private void showLevelCompleteDialog() {
        DialogInterface.OnClickListener onProceedClickListener = (dialog, which) -> {
            ((Activity) getContext()).finish();

            Intent intent = new Intent(getContext(), WhackAMoleActivity.class);
            getContext().startActivity(intent);
            isGameActive = true;
        };

        com.vvv.twogame.gametwo.LevelCompleteDialog dialog = new com.vvv.twogame.gametwo.LevelCompleteDialog(getContext(), ScoreManager.getScore(), onProceedClickListener);
        dialog.show();
    }

    private void showGameOverDialog(int finalScore) {
        DialogInterface.OnClickListener onRestartClickListener = (dialog, which) -> resetGame();
        com.vvv.twogame.gametwo.GameOverDialog dialog = new GameOverDialog(getContext(), onRestartClickListener);
        dialog.show(finalScore);
    }

    private void resetGame() {
        ((Activity) getContext()).finish();
        Intent intent = new Intent(getContext(), WhackAMoleActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    public void onQuitConfirmed() {
        ((Activity) getContext()).finish();
    }

    @Override
    public void onCancel() {
        isGamePaused = false;
    }

    @Override
    public void onBackPressed() {
        isGamePaused = true;
        QuitDialog quitDialog = new QuitDialog(this.getContext(), this);
        quitDialog.show();
    }
}
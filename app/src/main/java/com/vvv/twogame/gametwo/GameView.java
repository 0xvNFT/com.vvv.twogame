package com.vvv.twogame.gametwo;

import static com.vvv.twogame.gametwo.Constants.COLUMN_SPACING;
import static com.vvv.twogame.gametwo.Constants.HOLE_FRONT_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.HOLE_FRONT_WIDTH;
import static com.vvv.twogame.gametwo.Constants.HOLE_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.HOLE_WIDTH;
import static com.vvv.twogame.gametwo.Constants.MOLE_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.MOLE_WIDTH;
import static com.vvv.twogame.gametwo.Constants.NUM_COLUMNS;
import static com.vvv.twogame.gametwo.Constants.NUM_ROWS;
import static com.vvv.twogame.gametwo.Constants.ROW_SPACING;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.vvv.twogame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends View {
    private final List<Hole> holes;
    private final List<HoleFront> holesFront;
    private final List<Mole> moles;
    private final Handler handler = new Handler();
    private final Runnable moleRunnable;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int holeWidth = HOLE_WIDTH;
        int holeHeight = HOLE_HEIGHT;
        int holeFrontWidth = HOLE_FRONT_WIDTH;
        int holeFrontHeight = HOLE_FRONT_HEIGHT;
        int moleWidth = MOLE_WIDTH;
        int moleHeight = MOLE_HEIGHT;
        int columnSpacing = COLUMN_SPACING;
        int rowSpacing = ROW_SPACING;

        int totalWidth = NUM_COLUMNS * holeWidth + (NUM_COLUMNS - 1) * columnSpacing;
        int startX = (screenWidth - totalWidth) / 2;
        Bitmap holeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hole_half);
        Bitmap moleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mole_alive);
        Bitmap holeFrontBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hole_front);
        holes = new ArrayList<>();
        moles = new ArrayList<>();
        holesFront = new ArrayList<>();

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                int holeX = startX + col * (holeWidth + columnSpacing);
                int holeY = screenHeight - (row + 1) * (holeHeight + rowSpacing);
                Hole hole = new Hole(holeX, holeY, holeBitmap, holeWidth, holeHeight);
                holes.add(hole);


                int moleX = holeX + (holeWidth - moleWidth) / 2;
                int moleY = holeY + (holeHeight - moleHeight) / 2;
                Mole mole = new Mole(moleX, moleY, moleBitmap, moleWidth, moleHeight);
                moles.add(mole);

                int holeFrontX = holeX + (holeWidth - holeFrontWidth) / 2;
                int holeFrontY = moleY + (moleHeight - holeFrontHeight) + 5;
                HoleFront holeFront = new HoleFront(holeFrontX, holeFrontY, holeFrontBitmap, holeFrontWidth, holeFrontHeight);
                holesFront.add(holeFront);
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
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int row = 0; row < Constants.NUM_ROWS; row++) {
            for (int col = 0; col < Constants.NUM_COLUMNS; col++) {
                Hole hole = holes.get(row * Constants.NUM_COLUMNS + col);
                hole.draw(canvas);

                Mole mole = moles.get(row * Constants.NUM_COLUMNS + col);
                mole.draw(canvas);
                HoleFront holeFront = holesFront.get(row * Constants.NUM_COLUMNS + col);
                holeFront.draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();
            for (Mole mole : moles) {
                if (mole.contains(touchX, touchY)) {
                    mole.whack();
                    invalidate();

                }
            }
        }
        return true;
    }
}
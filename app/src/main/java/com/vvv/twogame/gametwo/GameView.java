package com.vvv.twogame.gametwo;

import static com.vvv.twogame.gametwo.Constants.COLUMN_SPACING;
import static com.vvv.twogame.gametwo.Constants.HOLE_HEIGHT;
import static com.vvv.twogame.gametwo.Constants.HOLE_WIDTH;
import static com.vvv.twogame.gametwo.Constants.NUM_COLUMNS;
import static com.vvv.twogame.gametwo.Constants.NUM_ROWS;
import static com.vvv.twogame.gametwo.Constants.ROW_SPACING;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.vvv.twogame.R;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    private final List<Hole> holes;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        int holeWidth = HOLE_WIDTH;
        int holeHeight = HOLE_HEIGHT;
        int columnSpacing = COLUMN_SPACING;
        int rowSpacing = ROW_SPACING;

        int totalWidth = NUM_COLUMNS * holeWidth + (NUM_COLUMNS - 1) * columnSpacing;
        int startX = (screenWidth - totalWidth) / 2;
        Bitmap holeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
        holes = new ArrayList<>();
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                int holeX = startX + col * (holeWidth + columnSpacing);
                int holeY = screenHeight - (row + 1) * (holeHeight + rowSpacing);
                Hole hole = new Hole(holeX, holeY, holeBitmap, holeWidth, holeHeight);
                holes.add(hole);
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (Hole hole : holes) {
            hole.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return false;
    }

}


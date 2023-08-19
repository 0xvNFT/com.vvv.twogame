package com.vvv.twogame.gametwo;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class WhackAMoleView extends View {
    private final List<GameObject> gameObjects = new ArrayList<>();
    private final int score = 0;
    GameOverDialog gameOverDialog = new GameOverDialog(getContext(), score, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
        //gameOverDialog.show();
    });
    LevelCompleteDialog levelCompleteDialog = new LevelCompleteDialog(getContext(), score, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
        //levelCompleteDialog.show();
    });


    public WhackAMoleView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return false;
    }

}


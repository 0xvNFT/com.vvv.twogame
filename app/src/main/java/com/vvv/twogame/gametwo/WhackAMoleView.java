package com.vvv.twogame.gametwo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class WhackAMoleView extends View {
    private final List<GameObjectWAM> gameObjects = new ArrayList<>();
    private final int score = 0;

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


package com.vvv.twogame.gameone;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreManager {
    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void drawScore(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(48);
        canvas.drawText("Score: " + score, 20, 50, paint);
    }
}


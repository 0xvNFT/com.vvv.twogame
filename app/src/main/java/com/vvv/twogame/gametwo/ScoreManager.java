package com.vvv.twogame.gametwo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreManager {
    private static int score;

    public ScoreManager() {
        score = 0;
    }

    public void draw(Canvas canvas) {
        Paint paintScore = new Paint();
        paintScore.setColor(Color.WHITE);
        paintScore.setTextSize(50);
        String scoreText = "Score: " + getScore();
        canvas.drawText(scoreText, canvas.getWidth() - 250, 50, paintScore);
    }

    public static int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
    }
}


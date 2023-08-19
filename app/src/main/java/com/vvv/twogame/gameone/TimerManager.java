package com.vvv.twogame.gameone;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class TimerManager {
    private long startTime;
    private long duration;
    private long remainingTime;

    public TimerManager() {
        startTime = System.currentTimeMillis();
        setRandomDuration();
    }

    private void setRandomDuration() {
        int[] durations = {30, 45, 60, 80, 100};
        int randomIndex = new Random().nextInt(durations.length);
        duration = durations[randomIndex] * 1000;
        remainingTime = duration;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        remainingTime = duration - (currentTime - startTime);

        if (remainingTime <= 0) {
            //setRandomDuration();
            startTime = currentTime;
        }
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void resetTimer() {
        startTime = System.currentTimeMillis();
        setRandomDuration();
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(48);

        long remainingTimeInSeconds = remainingTime / 1000;
        String timerText = "Time: " + remainingTimeInSeconds + "s";
        canvas.drawText(timerText, canvas.getWidth() - 300, 50, paint);
    }

    public boolean isTimeUp() {
        return remainingTime <= 0;
    }
}


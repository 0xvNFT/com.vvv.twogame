package com.vvv.twogame.gametwo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Health {
    private final int maxHealth;
    private int currentHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void draw(Canvas canvas) {
        Paint paintHealth = new Paint();
        paintHealth.setColor(Color.WHITE);
        paintHealth.setTextSize(50);
        String healthText = "Health: " + getCurrentHealth();
        canvas.drawText(healthText, 50, 50, paintHealth);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void reduceHealth() {
        if (currentHealth > 0) {
            currentHealth--;
        }
    }

    public void resetHealth() {
        currentHealth = maxHealth;
    }
}


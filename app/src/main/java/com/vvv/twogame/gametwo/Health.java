package com.vvv.twogame.gametwo;

public class Health {
    private final int maxHealth;
    private int currentHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
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


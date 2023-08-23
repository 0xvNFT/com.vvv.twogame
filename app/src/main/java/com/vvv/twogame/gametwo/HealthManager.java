package com.vvv.twogame.gametwo;

public class HealthManager {
    private final int maxHealth;
    private int currentHealth;

    public HealthManager(int maxHealth) {
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

    public boolean isAlive() {
        return currentHealth > 0;
    }
}

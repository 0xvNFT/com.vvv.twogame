package com.vvv.twogame.gameone;

public class HealthManager {
    private final int maxHealth;
    private int currentHealth;
    private boolean isBlinking = false;
    private long blinkStartTime = 0;

    public HealthManager(int initialHealth, int maxHealth) {
        this.currentHealth = initialHealth;
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void decreaseHealth(int amount) {
        if (!isBlinking) {
            currentHealth -= amount;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
            if (currentHealth > 0) {
                isBlinking = true;
                blinkStartTime = System.currentTimeMillis();
            }
        }
    }

    //will use for future if power ups functions are added ^^
    public void increaseHealth(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void startBlinking() {
        isBlinking = true;
        blinkStartTime = System.currentTimeMillis();
    }

    private void stopBlinking() {
        isBlinking = false;
    }

    public boolean isBlinking() {
        if (isBlinking) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - blinkStartTime;

            if (elapsedTime >= 3000) {
                isBlinking = false;
            }
        }
        return isBlinking;
    }

    public long getBlinkStartTime() {
        return blinkStartTime;
    }
}

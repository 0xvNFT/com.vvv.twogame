package com.vvv.twogame.gametwo;

public class ScoreManager {
    private int score;

    public ScoreManager() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
    }
}


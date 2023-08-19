package com.vvv.twogame.gametwo;

public class ScoreManager {
    private int score = 0;

    public void increaseScore(int points) {
        score += points;
    }

    public void decreaseScore(int points) {
        score -= points;
        if (score < 0) {
            score = 0;
        }
    }

    public int getScore() {
        return score;
    }
}


//package com.vvv.twogame.game1;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//public class ScoreManager {
//
//    private static final String PREFS_NAME = "GameScores";
//    private static final String HIGH_SCORE_KEY = "HighScore";
//
//    private int currentScore;
//    private int highScore;
//    private SharedPreferences sharedPreferences;
//
//    public ScoreManager(Context context) {
//        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0);
//    }
//
//    public int getCurrentScore() {
//        return currentScore;
//    }
//
//    public int getHighScore() {
//        return highScore;
//    }
//
//    public void updateScore(int points) {
//        currentScore += points;
//        if (currentScore > highScore) {
//            highScore = currentScore;
//            saveHighScore();
//        }
//    }
//
//    private void saveHighScore() {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(HIGH_SCORE_KEY, highScore);
//        editor.apply();
//    }
//
//    public void resetScore() {
//        currentScore = 0;
//    }
//}

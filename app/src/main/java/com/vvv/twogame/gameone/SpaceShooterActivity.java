package com.vvv.twogame.gameone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.vvv.twogame.R;

public class SpaceShooterActivity extends AppCompatActivity {
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaceshooter);

        //setContentView(new GameView(this, null));
    }

    public void increaseScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }
}

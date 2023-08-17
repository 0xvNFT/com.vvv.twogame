package com.vvv.twogame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.vvv.twogame.game2.WhackAMoleActivity;
import com.vvv.twogame.gameone.SpaceShooterActivity;

public class ChooseGameActivity extends AppCompatActivity {

    public ImageView game1_play, game2_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_choosegame);

        game1_play = findViewById(R.id.game1_play);
        game2_play = findViewById(R.id.game2_play);

        game1_play.setOnClickListener(v -> {
            @SuppressLint("IntentWithNullActionLaunch") Intent intent = new Intent(ChooseGameActivity.this, SpaceShooterActivity.class);
            startActivity(intent);
        });

        game2_play.setOnClickListener(v -> {
            @SuppressLint("IntentWithNullActionLaunch") Intent intent = new Intent(ChooseGameActivity.this, WhackAMoleActivity.class);
            startActivity(intent);
        });
    }
}

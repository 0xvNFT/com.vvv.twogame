//package com.vvv.twogame.game1;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.vvv.twogame.R;
//
//public class SpaceShooterActivity extends AppCompatActivity {
//    private SpaceShooterView spaceShooterView; // Reference to the game view
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_spaceshooter);
//
//        spaceShooterView = new SpaceShooterView(this);
//        setContentView(spaceShooterView);
//
//
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Resume the game when the activity is resumed
//        spaceShooterView.resumeGame();
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Pause the game when the activity is paused
//        spaceShooterView.pauseGame();
//
//    }
//}

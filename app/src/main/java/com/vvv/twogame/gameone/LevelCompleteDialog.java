package com.vvv.twogame.gameone;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class LevelCompleteDialog {
    private final Context context;
    private final int score;
    private final DialogInterface.OnClickListener onProceedClickListener;

    public LevelCompleteDialog(Context context, int score, DialogInterface.OnClickListener onProceedClickListener) {
        this.context = context;
        this.score = score;
        this.onProceedClickListener = onProceedClickListener;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Level Complete")
                .setMessage("Your Score: " + score)
                .setPositiveButton("Proceed to Next Level", onProceedClickListener)
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


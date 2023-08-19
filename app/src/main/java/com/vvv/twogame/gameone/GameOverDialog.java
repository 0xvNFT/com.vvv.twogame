package com.vvv.twogame.gameone;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class GameOverDialog {
    private final Context context;
    private final DialogInterface.OnClickListener onRestartClickListener;

    public GameOverDialog(Context context, DialogInterface.OnClickListener onRestartClickListener) {
        this.context = context;
        this.onRestartClickListener = onRestartClickListener;
    }

    public void show(int finalScore) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Game Over");
        builder.setMessage("You ran out of lives. \n Your final score: " + finalScore);

        builder.setPositiveButton("Restart", onRestartClickListener);
        builder.setNegativeButton("Quit", (dialog, which) -> ((Activity) context).finish());

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
}


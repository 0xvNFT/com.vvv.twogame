package com.vvv.twogame.gameone;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class ShowRulesDialog {
    private final Context context;
    private final DialogInterface.OnClickListener onProceedClickListener;

    public ShowRulesDialog(Context context, DialogInterface.OnClickListener onProceedClickListener) {
        this.context = context;
        this.onProceedClickListener = onProceedClickListener;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Game Rules");
        builder.setMessage("Survive until the timer reaches 0. \n Shoot the enemies and dodge them to survive. \n" +
                "You only have 5 lives. \n" + "Good luck!");

        builder.setPositiveButton("Start Game", onProceedClickListener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


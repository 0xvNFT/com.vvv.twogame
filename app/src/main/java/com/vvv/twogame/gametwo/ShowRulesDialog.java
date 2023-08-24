package com.vvv.twogame.gametwo;

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
        builder.setMessage("Hit the moles with hammer and avoid hitting the bombs. \n Wait until the timer reaches 0 to proceed to next level. \n" +
                "You only have 5 lives. \n" + "Good luck!");

        builder.setPositiveButton("Start Game", onProceedClickListener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

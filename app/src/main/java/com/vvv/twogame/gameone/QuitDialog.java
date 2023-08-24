package com.vvv.twogame.gameone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class QuitDialog {
    private final Context context;
    private final QuitDialogListener listener;

    public QuitDialog(Context context, QuitDialogListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Quit Game");
        builder.setMessage("Do you want to quit the game?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.onQuitConfirmed();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.onCancel();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


package com.vvv.twogame.gametwo;

import android.content.Context;
import android.content.DialogInterface;

public class GameOverDialog extends BaseDialog {
    public GameOverDialog(Context context, int score, DialogInterface.OnClickListener restartListener) {
        super(context);
        setTitle("Game Over");
        setMessage("Your Score: " + score);
        setCancelable(false);
        setPositiveButton("Restart", restartListener);
        setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // Handle exit action
            }
        });
    }
}


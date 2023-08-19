package com.vvv.twogame.gametwo;

import android.content.Context;
import android.content.DialogInterface;

public class LevelCompleteDialog extends BaseDialog {
    public LevelCompleteDialog(Context context, int score, DialogInterface.OnClickListener nextLevelListener) {
        super(context);
        setTitle("Level Complete");
        setMessage("Your Score: " + score);
        setCancelable(false);
        setPositiveButton("Next Level", nextLevelListener);
    }
}


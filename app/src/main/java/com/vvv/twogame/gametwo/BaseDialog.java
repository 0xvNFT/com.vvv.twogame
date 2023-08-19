package com.vvv.twogame.gametwo;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class BaseDialog extends AlertDialog.Builder {
    protected BaseDialog(Context context) {
        super(context);
    }

    protected void setPositiveButton(String text, DialogInterface.OnClickListener listener) {
        this.setPositiveButton(text, listener);
    }

    protected void setNegativeButton(String text, DialogInterface.OnClickListener listener) {
        this.setNegativeButton(text, listener);
    }
}


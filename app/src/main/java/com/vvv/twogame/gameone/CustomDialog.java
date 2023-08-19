package com.vvv.twogame.gameone;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;

import java.util.Objects;

public class CustomDialog extends Dialog {
    //use this for custom dialog/alert dialog ^^ -jayson
    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    public void dismiss() {
        Objects.requireNonNull(getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        super.dismiss();
    }
}


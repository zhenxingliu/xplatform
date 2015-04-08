package com.x.platform.mobile.common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.x.platform.mobile.R;

public final class CustomDialog {

    private static void dialogTitleLineColor(Context context, Dialog dialog, int color) {
        String dividers[] = {
                "android:id/titleDividerTop", "android:id/titleDivider"
        };

        for (int i = 0; i < dividers.length; ++i) {
            int divierId = context.getResources().getIdentifier(dividers[i], null, null);
            View divider = dialog.findViewById(divierId);
            if (divider != null) {
                divider.setBackgroundColor(color);
            }
        }
    }

    public static void dialogTitleLineColor(Context context, Dialog dialog) {
        if (dialog != null)
            dialogTitleLineColor(context, dialog, context.getResources().getColor(R.color.green));
    }
}
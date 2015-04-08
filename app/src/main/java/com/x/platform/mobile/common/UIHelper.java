package com.x.platform.mobile.common;

import android.app.Activity;
import android.widget.Toast;

import com.x.platform.mobile.LoginActivity;

/**
 * Created by 刘 on 2015/3/30.
 */
public class UIHelper {

    public static void ToastMessage(Activity activity, String msg) {
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
    }
}

package com.x.platform.mobile.common;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by 刘 on 2015/3/31.
 */
public class Global {

    public static final String HOST="http://xa01.zoneland.net:9080";

    public static final String SUCCESS="success";

    public static final String ERROR="error";

    public static void errorLog(Exception e) {
        e.printStackTrace();
        Log.e("", "" + e);
    }

    public static String getErrorMsg(JSONObject json) {
        return null;
    }
}

package com.x.platform.mobile.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络请求回调接口
 * @author 刘
 */
public interface NetworkCallback {

    void parseJson(int code, JSONObject response, String tag, int pos, Object data) throws JSONException;


    void getNetwork(String uri, String tag);
}

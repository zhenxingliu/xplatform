package com.x.platform.mobile.core;

import android.os.Bundle;

import com.loopj.android.http.RequestParams;
import com.x.platform.mobile.network.NetworkCallback;
import com.x.platform.mobile.network.NetworkImpl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 默认Activity类
 * 实现网络调用及回调接口
 * Created by 刘 on 2015/3/31.
 *
 */
public class DefaultActionBarActivity extends BaseActionBarActivity implements NetworkCallback {

    NetworkImpl networkImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkImpl = new NetworkImpl(this,this);
        initSetting();

    }

    protected void initSetting() {
        networkImpl.initSetting();
    }

    @Override
    public void parseJson(int code, JSONObject response, String tag, int pos, Object data) throws JSONException {

    }

    protected void getNextPageNetwork(String url, final String tag) {
        networkImpl.getNextPageNetwork(url, tag);
    }

    protected void postNetwork(String url, RequestParams params, final String tag) {
        networkImpl.loadData(url, params, tag, -1, null, NetworkImpl.Request.Post);
    }

    protected void postNetwork(String url, RequestParams params, final String tag, int dataPos, Object data) {
        networkImpl.loadData(url, params, tag, dataPos, data, NetworkImpl.Request.Post);
    }

    @Override
    public void getNetwork(String url, final String tag) {
        networkImpl.loadData(url, null, tag, -1, null, NetworkImpl.Request.Get);
    }

    protected void getNetwork(String url, final String tag, int dataPos, Object data) {
        networkImpl.loadData(url, null, tag, dataPos, data, NetworkImpl.Request.Get);
    }

    protected void putNetwork(String url, RequestParams params, final String tag) {
        networkImpl.loadData(url, params, tag, -1, null, NetworkImpl.Request.Put);
    }

    protected void putNetwork(String url, final String tag, int dataPos, Object data) {
        networkImpl.loadData(url, null, tag, dataPos, data, NetworkImpl.Request.Put);
    }

    protected void deleteNetwork(String url, final String tag) {
        networkImpl.loadData(url, null, tag, -1, null, NetworkImpl.Request.Delete);
    }

    protected void deleteNetwork(String url, final String tag, Object id) {
        networkImpl.loadData(url, null, tag, -1, id, NetworkImpl.Request.Delete);
    }

    protected void deleteNetwork(String url, final String tag, int dataPos, Object id) {
        networkImpl.loadData(url, null, tag, dataPos, id, NetworkImpl.Request.Delete);
    }


    protected boolean isLoadingLastPage(String tag) {
        return networkImpl.isLoadingLastPage(tag);
    }

    protected boolean isLoadingFirstPage(String tag) {
        return networkImpl.isLoadingFirstPage(tag);
    }






}

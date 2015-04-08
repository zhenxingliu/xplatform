package com.x.platform.mobile.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.x.platform.mobile.common.Global;
import com.x.platform.mobile.network.NetworkCallback;
import com.x.platform.mobile.network.NetworkImpl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 刘 on 2015/3/31.
 */
public class DefaultBaseFragment extends BaseFragment implements NetworkCallback {

    private NetworkImpl networkImpl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkImpl = new NetworkImpl(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initSetting();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    protected void initSetting() {
        networkImpl.initSetting();
    }


    @Override
    public void parseJson(int code, JSONObject response, String tag, int pos, Object data) throws JSONException {

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

    public void putNetwork(String url, final String tag) {
        networkImpl.loadData(url, null, tag, -1, null, NetworkImpl.Request.Put);
    }

    public void putNetwork(String url, final String tag, int dataPos, Object data) {
        networkImpl.loadData(url, null, tag, dataPos, data, NetworkImpl.Request.Put);
    }

    public void putNetwork(String url, RequestParams params, final String tag, Object data) {
        networkImpl.loadData(url, params, tag, -1, data, NetworkImpl.Request.Put);
    }

    public void deleteNetwork(String url, final String tag) {
        networkImpl.loadData(url, null, tag, -1, null, NetworkImpl.Request.Delete);
    }

    public void deleteNetwork(String url, final String tag, int dataPos, Object data) {
        networkImpl.loadData(url, null, tag, dataPos, data, NetworkImpl.Request.Delete);
    }

    public void deleteNetwork(String url, final String tag, Object data) {
        networkImpl.loadData(url, null, tag, -1, data, NetworkImpl.Request.Delete);
    }

    protected void getNextPageNetwork(String url, final String tag) {
        networkImpl.getNextPageNetwork(url, tag);
    }

    protected boolean isLoadingLastPage(String tag) {
        return networkImpl.isLoadingLastPage(tag);
    }

    protected boolean isLoadingFirstPage(String tag) {
        return networkImpl.isLoadingFirstPage(tag);
    }

    protected void showErrorMsg(int code, JSONObject json) {
        if (code == NetworkImpl.NETWORK_ERROR) {
            showButtomToast("连接服务器失败，请检查网络或稍后重试");
        } else {
            String msg = Global.getErrorMsg(json);
            if (!msg.isEmpty()) {
                showButtomToast(msg);
            }
        }
    }
}

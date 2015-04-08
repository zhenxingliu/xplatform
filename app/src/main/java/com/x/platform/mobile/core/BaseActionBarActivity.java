package com.x.platform.mobile.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.x.platform.mobile.common.CustomDialog;
import com.x.platform.mobile.common.ImageLoadTool;
import com.x.platform.mobile.network.NetworkCallback;
import com.x.platform.mobile.network.NetworkImpl;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

import static com.x.platform.mobile.common.CustomDialog.dialogTitleLineColor;

/**
 * activity基础类，主要提供如下功能：
 * 1.基础进度条
 * 2.通用对话框
 * 3.通用图片请求
 * 4.ButterKnife注解注入
 * Created by 刘 on 2015/3/30.
 */
public class BaseActionBarActivity extends ActionBarActivity implements ActivityHelperInterface {

    private ProgressDialog mProgressDialog;

    private ImageLoadTool imageLoadTool = new ImageLoadTool();

    private ActivityHelper _helper = new ActivityHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO 友盟等代理启动
    }

    @Override
    protected void onPause() {
        super.onPause();
        //TODO 友盟等代理暂停
    }

    @Override
    protected void onDestroy() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        super.onDestroy();
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
        //TODO 是否可以在这里写局的LOGO、标题等的设置?
    }

    @Override
    public XApplicationContext getXApplicationContext() {
        return _helper.getXApplicationContext();
    }

    @Override
    public Activity getActivity() {
        return _helper.getActivity();
    }

    protected ImageLoadTool getImageLoad() {
        return imageLoadTool;
    }

    /**
     * 显示进度条
     * @param show
     */
    protected void showProgressBar(boolean show) {
        if (show) {
            mProgressDialog.show();
        } else {
            mProgressDialog.hide();
        }
    }

    public final void dialogTitleLineColor(Dialog dialog) {
        CustomDialog.dialogTitleLineColor(this, dialog);
    }


    protected void showDialog(String msg, DialogInterface.OnClickListener clickOk) {
      showDialog(null,msg,clickOk);
    }

    /**
     * 显示对话框
     * @param title 标题
     * @param msg 内容
     * @param clickOk 确定事件
     */
    protected void showDialog(String title, String msg, DialogInterface.OnClickListener clickOk) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = null;
        if(StringUtils.isEmpty(title)){
            dialog = builder.setTitle(msg)
                    .setPositiveButton("确定", clickOk)
                    .setNegativeButton("取消", null)
                    .show();
        }else{
            dialog=builder.setTitle(title)
                    .setMessage(msg)
                    .setPositiveButton("确定", clickOk)
                    .setNegativeButton("取消", null)
                    .show();
        }
        dialogTitleLineColor(dialog);
    }

    /**
     * 底部显示Toast
     * @param msg
     */
    protected void showButtomToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 中部显示Toast
     * @param msg
     */
    protected void showMiddleToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     *加载图片
     * @param view
     * @param url
     */
    protected void imagefromNetwork(ImageView view, String url) {
        imageLoadTool.loadImageFromUrl(view, url);
    }

    /**
     *
     * @param view
     * @param url
     * @param options
     */
    protected void imagefromNetwork(ImageView view, String url, DisplayImageOptions options) {
        imageLoadTool.loadImageFromUrl(view, url, options);
    }


    /** 将菜单显示在actionbar上，而不是在底部*/
    protected void requestActionBarMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");

            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            // presumably, not relevant
        }
    }

}

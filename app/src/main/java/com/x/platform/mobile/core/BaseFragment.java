package com.x.platform.mobile.core;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.x.platform.mobile.common.CustomDialog;
import com.x.platform.mobile.common.ImageLoadTool;

import org.json.JSONObject;

/**
 * Created by 刘 on 2015/3/31.
 */
public class BaseFragment extends Fragment {

    private ImageLoadTool imageLoadTool = new ImageLoadTool();

    protected LayoutInflater mInflater;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(getActivity());
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public ActionBarActivity getActionBarActivity() {
        return (ActionBarActivity) getActivity();
    }


    protected void showProgressBar(boolean show) {
        if (show) {
            mProgressDialog.show();
        } else {
            mProgressDialog.hide();
        }
    }

    protected boolean progressBarIsShowing() {
        return mProgressDialog.isShowing();
    }

    protected ImageLoadTool getImageLoad() {
        return imageLoadTool;
    }

    protected void showDialog(String msg, DialogInterface.OnClickListener clickOk) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.setTitle(msg)
                .setPositiveButton("确定", clickOk)
                .setNegativeButton("取消", null)
                .show();
        CustomDialog.dialogTitleLineColor(getActivity(), dialog);
    }

    protected void showDialog(String title, String msg, DialogInterface.OnClickListener clickOk) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", clickOk)
                .setNegativeButton("取消", null)
                .show();
        CustomDialog.dialogTitleLineColor(getActivity(), dialog);
    }

    protected void showButtomToast(String msg) {
        Toast toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void showMiddleToast(String msg) {
        Toast toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }





}

package com.x.platform.mobile.core;

import android.app.Activity;

/**
 * Created by 刘 on 2015/3/30.
 */
public class ActivityHelper implements ActivityHelperInterface {

    private Activity _mActivity;

    public ActivityHelper(Activity mActivity){
        this._mActivity = mActivity;
    }

    @Override
    public XApplicationContext getXApplicationContext() {
        return (XApplicationContext) _mActivity.getApplication();
    }

    @Override
    public Activity getActivity() {
        return _mActivity;
    }
}

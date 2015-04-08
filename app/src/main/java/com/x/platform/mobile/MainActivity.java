package com.x.platform.mobile;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.x.platform.mobile.core.DefaultActionBarActivity;
import com.x.platform.mobile.ui.BlankFragment;
import com.x.platform.mobile.ui.NavigationDrawerFragment;


public class MainActivity extends DefaultActionBarActivity  implements NavigationDrawerFragment.NavigationDrawerFragmentCallback,BlankFragment.OnFragmentInteractionListener {


    public static final String TAG = "MainActivity";

    String mTitle;

    NavigationDrawerFragment navigationDrawerFragment;

    ViewGroup drawer_layout;

    boolean mFirstEnter = true;

    int mSelectPos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstEnter = (savedInstanceState == null);

        if (savedInstanceState != null) {
            mSelectPos = savedInstanceState.getInt("pos", 0);
            mTitle = savedInstanceState.getString("mTitle");
        }
        navigationDrawerFragment= (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        if (mFirstEnter) {
            onNavigationDrawerItemSelected(0);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos", mSelectPos);
//        outState.putSerializable("mPushOpened", mPushOpened);
        outState.putString("mTitle", mTitle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSelectPos = savedInstanceState.getInt("pos", 0);
        mTitle = savedInstanceState.getString("mTitle");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

        mSelectPos = position;
        switch (position){
            case 0:
                BlankFragment fragment = new BlankFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer_content,fragment).commit();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }

    }

    public void restoreActionBar() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package com.elitemobiletechnology.gamecenterapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.Constants;

/**
 * Created by SteveYang on 3/6/15.
 */

public class ShowGameActivity extends ActionBarActivity {
    private static final String TAG = "ShowGameActivity";
    ActionBar actionBar;
    FragmentManager fragmentManager;
    Fragment listFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_game);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back_button);
        addGameListFragment();
    }

    private void addGameListFragment() {
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IS_GAME_RATING, false);
        listFragment.setArguments(bundle);
        fragmentTransaction.add(android.R.id.content, listFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addFormFragment() {
        FormFragment formFragment = new FormFragment();
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(android.R.id.content, formFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "currently there are " + fragmentManager.getBackStackEntryCount());

                if (fragmentManager.getBackStackEntryCount() > 1) {
                    fragmentManager.popBackStack();
                } else {
                    super.onBackPressed();
                }
                break;
            case R.id.buttonAdd:
                if (fragmentManager != null) {
                    addFormFragment();
                }
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_show_game, menu);
        return true;
    }
}


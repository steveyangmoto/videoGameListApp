package com.elitemobiletechnology.gamecenterapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils.Constants;


public class RateGamesActivity extends ActionBarActivity {
    private static final String TAG = "RateGamesActivity";
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_games);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(R.color.white));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back_button);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IS_GAME_RATING,true);
        fragment.setArguments(bundle);
        fragmentTransaction.add(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}

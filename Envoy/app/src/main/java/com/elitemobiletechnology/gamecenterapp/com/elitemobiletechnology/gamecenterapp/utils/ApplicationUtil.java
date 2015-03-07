package com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SteveYang on 3/6/15.
 */
public class ApplicationUtil {

    public static SharedPreferences getSharedPref(Context c){
        return c.getSharedPreferences(
                c.getPackageName(), Context.MODE_PRIVATE);
    }

    public static void saveToSharedPrefs(Context c,String key,String value){
        SharedPreferences.Editor editor;
        editor = getSharedPref(c).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context,String key){
        return getSharedPref(context).getString(key,null);
    }
}


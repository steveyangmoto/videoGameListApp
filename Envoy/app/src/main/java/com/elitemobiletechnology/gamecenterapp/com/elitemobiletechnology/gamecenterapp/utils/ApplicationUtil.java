package com.elitemobiletechnology.gamecenterapp.com.elitemobiletechnology.gamecenterapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SteveYang on 3/6/15.
 */
public class ApplicationUtil {

    public static SharedPreferences getSharedPref(Context c) {
        return c.getSharedPreferences(
                c.getPackageName(), Context.MODE_PRIVATE);
    }

    public static void saveToSharedPrefs(Context c, String key, String value) {
        SharedPreferences.Editor editor;
        editor = getSharedPref(c).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key) {
        return getSharedPref(context).getString(key, null);
    }

    public static boolean saveFile(Bitmap bitmap,String filename) {
        File sd = Environment.getExternalStorageDirectory();
        File dest = new File(sd, filename);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Drawable getDrawableFromFile(String fileName){
        Drawable d = Drawable.createFromPath(getAbsoluteFilePath(fileName));
        return d;
    }

    public static String getAbsoluteFilePath(String fileName){
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +fileName;
        return filePath;
    }

}


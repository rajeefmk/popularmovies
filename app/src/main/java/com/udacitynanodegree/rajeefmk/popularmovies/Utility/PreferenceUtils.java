package com.udacitynanodegree.rajeefmk.popularmovies.Utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rmk on 14/3/16.
 */
public class PreferenceUtils {

    @SuppressLint("CommitPrefEdits")
    public static void setStringSet(Context context, String keyId, HashSet<String> moviesIdSet) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(Constants.SETTINGS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(keyId, moviesIdSet);
        editor.commit();
    }

    public static HashSet<String> getStringSet(Context context, String keyId, Set<String> defaultValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(Constants.SETTINGS_NAME, Context.MODE_PRIVATE);
        return (HashSet<String>) sharedPreferences.getStringSet(keyId, defaultValue);
    }
}

package com.example.trendingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.securepreferences.SecurePreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class SharedPreferenceUtil {

    private static SharedPreferences preferences;
    public static final String Holding_Data = "holding_data";

    public SharedPreferenceUtil() {
    }

    private static SharedPreferences getPreferences(Context context) {
        if (preferences == null) {
            preferences = new SecurePreferences(context,
                    ConfigUtil.SHARED_PREFERENCES_PASSWORD,
                    ConfigUtil.SHARED_PREFERENCES_FILE_NAME);
        }
        return preferences;
    }

    // Getters
    public static String getStringSharedPreference(Context context, String key) {
        return getPreferences(context).getString(key, "");
    }

    public static boolean getBooleanSharedPreference(Context context, String key) {
        return getPreferences(context).getBoolean(key, false);
    }

    public static int getIntSharedPreference(Context context, String key) {
        return getPreferences(context).getInt(key, -1);
    }

    public static long getLongSharedPreference(Context context, String key) {
        return getPreferences(context).getLong(key, -1);
    }

    public static float getFloatSharedPreference(Context context, String key) {
        return getPreferences(context).getFloat(key, -1);
    }

    public static Set<String> getStringSetSharedPreference(Context context, String key) {
        return getPreferences(context).getStringSet(key, null);
    }

    public static <T> ArrayList<T> getSharedPrefObject(Context context, String key, TypeToken<ArrayList<T>> value){
        Gson gson = new Gson();
        SharedPreferences mPrefs = getPreferences(context);
        String json = mPrefs.getString(key, "");
        return gson.fromJson(json, value.getType());
    }

    // Setters
    public static <T> void setSharedPrefObject(Context context, String key, List<T> value){
        SharedPreferences.Editor edit = getPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        edit.putString(key, json);
        edit.apply();
    }

    public static void setSharedPreference(Context context, String key, String value) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static void setSharedPreference(Context context, String key, boolean value) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static void setSharedPreference(Context context, String key, int value) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static void setSharedPreference(Context context, String key, long value) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public static void setSharedPreference(Context context, String key, float value) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public static void setSharedPreference(Context context, String key, Set<String> value) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.putStringSet(key, value);
        edit.apply();
    }

    public static void remove(Context context, String key) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        edit.remove(key);
        edit.apply();
    }


}

package com.guralnya.weatherforever.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static final String PREFERENCES_NAME = "preferences";
    private static final String UPDATE_ONLY_BY_WIFI = "updateOnlyByWiFi";
    private static final String UPDATE_START_UP = "updateStartUp";
    private static final String ASK_LEAVING = "askLeaving";

    private static final String WAS_SET_CITY = "settedCity";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean getUpdateOnlyByWifi(Context context) {
        return getPreferences(context).getBoolean(UPDATE_ONLY_BY_WIFI, false);
    }

    public static void setUpdateOnlyByWifi(Context context, boolean set) {
        getPreferences(context).edit().putBoolean(UPDATE_ONLY_BY_WIFI, set).apply();
    }

    public static boolean getUpdateStartUp(Context context) {
        return getPreferences(context).getBoolean(UPDATE_START_UP, false);
    }

    public static void setUpdateStartUp(Context context, boolean set) {
        getPreferences(context).edit().putBoolean(UPDATE_START_UP, set).apply();
    }

    public static boolean getAskLeaving(Context context) {
        return getPreferences(context).getBoolean(ASK_LEAVING, false);
    }

    public static void setAskLeaving(Context context, boolean set) {
        getPreferences(context).edit().putBoolean(ASK_LEAVING, set).apply();
    }

    public static String getWasSetCity(Context context) {
        return getPreferences(context).getString(WAS_SET_CITY, "");
    }

    public static void setCity(Context context, String city, String country) {
        getPreferences(context).edit().putString(WAS_SET_CITY, city + "," + country).apply();
    }
}

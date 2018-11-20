package com.guralnya.weatherforever.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static final String PREFERENCES_NAME = "preferences";
    private static final String UPDATE_ONLY_BY_WIFI = "updateOnlyByWiFi";
    private static final String UPDATE_START_UP = "updateStartUp";
    private static final String ASK_LEAVING = "askLeaving";

    private static final String LOCATION_SELECTION = "locationSelection";
    private static final String WAS_SET_COUNTRY = "wasSetCountry";
    private static final String WAS_SET_CITY = "wasSetCity";
    private static boolean UPDATED_FORECAST_TODAY_THIS_SESSION = false;

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
        return getPreferences(context).getBoolean(UPDATE_START_UP, true);
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

    public static int getLocationSelection(Context context) {
        return getPreferences(context).getInt(LOCATION_SELECTION, Constants.MANUAL_LOCATION);
    }

    public static void setLocationSelection(Context context, int set) {
        getPreferences(context).edit().putInt(LOCATION_SELECTION, set).apply();
    }

    public static String getWasSetCountry(Context context) {
        return getPreferences(context).getString(WAS_SET_COUNTRY, "ua");
    }

    public static void setCountry(Context context, String country) {
        getPreferences(context).edit().putString(WAS_SET_COUNTRY, country).apply();
    }

    public static String getWasSetCity(Context context) {
        return getPreferences(context).getString(WAS_SET_CITY, "Kremenchuk");
    }

    public static void setCity(Context context, String city) {
        getPreferences(context).edit().putString(WAS_SET_CITY, city).apply();
    }

    public static void resetLocation(Context context){
        setCountry(context, "");
        setCity(context, "");
    }

    public static void setUpdatedForecastTodayThisSession(boolean updatedForecastTodayThisSession) {
        UPDATED_FORECAST_TODAY_THIS_SESSION = updatedForecastTodayThisSession;
    }

    public static boolean isUpdatedForecastTodayThisSession() {
        return UPDATED_FORECAST_TODAY_THIS_SESSION;
    }


}

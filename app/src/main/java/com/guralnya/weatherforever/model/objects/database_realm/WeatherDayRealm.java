package com.guralnya.weatherforever.model.objects.database_realm;

import io.realm.RealmObject;

public class WeatherDayRealm extends RealmObject {

    private String mTemperature;
    private String mPressure;
    private String mHumidity;
    private String mWindSpeed;
    private String mCity;
    private String mGetIconUrl;
    private long mTimeStamp;

    public WeatherDayRealm(){}

    public WeatherDayRealm(String temperature,
                           String pressure,
                           String humidity,
                           String windSpeed,
                           String city,
                           String getIconUrl,
                           long timeStamp) {
        mTemperature = temperature;
        mPressure = pressure;
        mHumidity = humidity;
        mWindSpeed = windSpeed;
        mCity = city;
        mGetIconUrl = getIconUrl;
        mTimeStamp = timeStamp;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String temperature) {
        mTemperature = temperature;
    }

    public String getPressure() {
        return mPressure;
    }

    public void setPressure(String pressure) {
        mPressure = pressure;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String humidity) {
        mHumidity = humidity;
    }

    public String getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        mWindSpeed = windSpeed;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getGetIconUrl() {
        return mGetIconUrl;
    }

    public void setGetIconUrl(String getIconUrl) {
        mGetIconUrl = getIconUrl;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }
}

package com.guralnya.weatherforever.model.repository;

import com.guralnya.weatherforever.model.objects.WeatherDay;

public class SessionRepository implements IDownloadWeather {

    private static double pLongitude;
    private static double pLatitude;

    private static WeatherDay pTodayForecast;

    public static WeatherDay getTodayForecast() {
        return pTodayForecast;
    }

    public static void setTodayForecast(WeatherDay todayForecast) {
        pTodayForecast = todayForecast;
    }

    public static double getLatitude() {
        return pLatitude;
    }

    public static void setLatitude(double latitude) {
        pLatitude = latitude;
    }

    public static double getLongitude() {
        return pLongitude;
    }

    public static void setLongitude(double longitude) {
        pLongitude = longitude;
    }


    @Override
    public void getTodayForecastListener(WeatherDay weatherDay) {
        pTodayForecast = weatherDay;
    }
}

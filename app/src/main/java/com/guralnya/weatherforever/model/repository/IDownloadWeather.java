package com.guralnya.weatherforever.model.repository;

import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;

public interface IDownloadWeather {
    void getTodayForecastListener(WeatherDayRealm weatherDay);
}

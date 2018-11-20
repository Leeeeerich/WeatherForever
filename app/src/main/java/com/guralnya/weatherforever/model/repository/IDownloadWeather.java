package com.guralnya.weatherforever.model.repository;

import com.guralnya.weatherforever.model.objects.WeatherDay;

public interface IDownloadWeather {
    void getTodayForecastListener(WeatherDay weatherDay);
}

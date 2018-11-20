package com.guralnya.weatherforever.presenters;

import com.guralnya.weatherforever.model.objects.WeatherDay;

public interface ITodayForecastPresenter {
    void getForestTodayListener(WeatherDay weatherDay);
}

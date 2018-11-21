package com.guralnya.weatherforever.presenters;

import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;

public interface ITodayForecastPresenter {
    void getForestTodayListener(WeatherDayRealm weatherDay);
}

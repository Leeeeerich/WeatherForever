package com.guralnya.weatherforever.presenters;

import com.guralnya.weatherforever.BaseApplication;
import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.repository.DownloadWeather;
import com.guralnya.weatherforever.model.repository.IDownloadWeather;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;

public class TodayForecastPresenter implements IDownloadWeather {

    private static TodayForecastPresenter mInstance;
    public static TodayForecastPresenter getInstance(){
        if(mInstance == null){
            mInstance = new TodayForecastPresenter();
        }
        return mInstance;
    }

    private ITodayForecastPresenter mITodayForecastPresenter;
    public void setITodayForecastListener(ITodayForecastPresenter ITodayForecastPresenter) {
        mITodayForecastPresenter = ITodayForecastPresenter;
    }

    public void getTodayForecast(){
        DownloadWeather downloadWeather = new DownloadWeather();
        downloadWeather.setIDownloadWeatherListener(this);
        if(SettingsManager.getLocationSelection(BaseApplication.context) == Constants.MANUAL_LOCATION){
            downloadWeather.getWeatherTodayByCity(
                    SettingsManager.getWasSetCity(BaseApplication.context),
                    SettingsManager.getWasSetCountry(BaseApplication.context));
        } else {
            //TODO By position GPS
        }
    }

    @Override
    public void getTodayForecastListener(WeatherDay weatherDay) {
        mITodayForecastPresenter.getForestTodayListener(weatherDay);
    }
}

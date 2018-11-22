package com.guralnya.weatherforever;

import android.app.Application;
import android.content.Context;

import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;
import com.guralnya.weatherforever.model.repository.DownloadWeather;
import com.guralnya.weatherforever.model.repository.IDownloadWeather;
import com.guralnya.weatherforever.model.repository.SessionRepository;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getBaseContext();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("weather.realm").build();
        Realm.setDefaultConfiguration(config);

        //Realm.deleteRealm(config);

        if (SettingsManager.getUpdateStartUp(getBaseContext())) {
            if (!SettingsManager.getUpdateOnlyByWifi(getBaseContext())) {
                if(SettingsManager.getLocationSelection(getBaseContext()) == Constants.AUTO_LOCATION){
                    DownloadWeather.getWeatherTodayByPosition(SessionRepository.getLatitude(),
                            SessionRepository.getLongitude());

                    DownloadWeather.getWeatherWeekByPosition(
                            SessionRepository.getLatitude(),
                            SessionRepository.getLongitude());
                } else {
                    DownloadWeather.getWeatherTodayByCity(
                            SettingsManager.getWasSetCity(getBaseContext()),
                            SettingsManager.getWasSetCountry(getBaseContext()));

                    DownloadWeather.getWeatherWeekByCity(
                            SettingsManager.getWasSetCity(getBaseContext()),
                            SettingsManager.getWasSetCountry(getBaseContext()));
                }
            } else {
                //TODO Update forecast only by WiFi
            }
        }
    }
}

package com.guralnya.weatherforever;

import android.app.Application;

import com.guralnya.weatherforever.model.repository.DownloadWeather;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("weather.realm").build();
        Realm.setDefaultConfiguration(config);

        //Realm.deleteRealm(config);

        if (SettingsManager.getUpdateStartUp(getBaseContext())) {
            if (!SettingsManager.getUpdateOnlyByWifi(getBaseContext())) {
                if(SettingsManager.getLocationSelection(getBaseContext()) == Constants.AUTO_LOCATION){
                    //getLocation
                   // DownloadWeather.getWeatherWeekByPosition();
                } else {
                    DownloadWeather.getWeatherWeekByCity(
                            SettingsManager.getWasSetCity(getBaseContext()),
                            SettingsManager.getWasSetCountry(getBaseContext()));
                }
            } else {
                //обновление только через wifi
            }
        }

    }
}

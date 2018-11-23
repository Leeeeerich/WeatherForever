package com.guralnya.weatherforever.model.repository;

import android.content.Context;

import com.guralnya.weatherforever.model.objects.database_realm.CountriesRealm;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;

import io.realm.Realm;
import io.realm.RealmResults;


public class Repository {

    public static RealmResults<CountriesRealm> getCountries() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CountriesRealm> countriesRealmList = realm.where(CountriesRealm.class).findAll();
        if (countriesRealmList.size() != 0) {
            return countriesRealmList;
        } else {
            DownloadCities.getCountries();
        }
        return null;
    }

    public static void getWeather(Context context){
        if (SettingsManager.getLocationSelection(context) == Constants.AUTO_LOCATION) {
            DownloadWeather.getWeatherTodayByPosition(SessionRepository.getLatitude(),
                    SessionRepository.getLongitude());

            DownloadWeather.getWeatherWeekByPosition(
                    SessionRepository.getLatitude(),
                    SessionRepository.getLongitude());
        } else {
            DownloadWeather.getWeatherTodayByCity(
                    SettingsManager.getWasSetCity(context),
                    SettingsManager.getWasSetCountry(context));

            DownloadWeather.getWeatherWeekByCity(
                    SettingsManager.getWasSetCity(context),
                    SettingsManager.getWasSetCountry(context));
        }
    }
}

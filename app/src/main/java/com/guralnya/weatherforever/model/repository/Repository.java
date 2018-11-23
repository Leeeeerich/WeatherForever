package com.guralnya.weatherforever.model.repository;

import com.guralnya.weatherforever.model.objects.database_realm.CountriesRealm;

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

}

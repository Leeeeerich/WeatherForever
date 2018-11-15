package com.guralnya.weatherforever;

import android.app.Application;

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
    }
}
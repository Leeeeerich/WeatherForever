package com.guralnya.weatherforever;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.guralnya.weatherforever.model.repository.DownloadCities;
import com.guralnya.weatherforever.model.repository.DownloadWeather;
import com.guralnya.weatherforever.model.repository.Repository;
import com.guralnya.weatherforever.model.repository.SessionRepository;
import com.guralnya.weatherforever.model.utils.Tools;
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

        if(Tools.hasConnection(context)) {
            if (SettingsManager.getUpdateStartUp(getBaseContext())) {
                if (!SettingsManager.getUpdateOnlyByWifi(getBaseContext())) {
                    DownloadCities.getCountries();

                    Repository.getWeather(context);

                } else {
                    //TODO Update forecast only by WiFi
                }
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.not_connection),
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
    }
}

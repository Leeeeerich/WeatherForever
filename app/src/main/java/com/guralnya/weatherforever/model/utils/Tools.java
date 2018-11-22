package com.guralnya.weatherforever.model.utils;

import android.util.Log;

import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Tools {

    public static List<WeatherDayRealm> hourlyForecastConvertToDaily(List<WeatherDay> weatherDayList) {
        List<WeatherDayRealm> weatherDayRealmList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        int minTemp = 255;
        int maxTemp = -255;
        int humidity = 0;
        int pressure = 0;
        for (WeatherDay weatherDay : weatherDayList) {


            if(minTemp > weatherDay.getTemp()) minTemp = weatherDay.getTemp();
            if(maxTemp < weatherDay.getTemp()) maxTemp = weatherDay.getTemp();
            Log.e("qwerty", "\nTemp = " + weatherDay.getTemp() + "\nminTemp = " + minTemp + "\nmaxTemp = " + maxTemp);

            if(humidity <= weatherDay.getHumidity()) humidity = weatherDay.getHumidity();
            if(pressure <= weatherDay.getPressure()) pressure = weatherDay.getPressure();

            if (simpleDateFormat.format(weatherDay.getTimestamp() * 1000).equals("00")) {
                WeatherDayRealm weatherDayRealm = new WeatherDayRealm(
                        String.valueOf(minTemp),
                        String.valueOf(maxTemp),
                        String.valueOf(pressure),
                        String.valueOf(humidity),
                        weatherDay.getWindSpeed(),
                        weatherDay.getCity(),
                        weatherDay.getIconUrl(),
                        weatherDay.getTimestamp() - 3600
                );
                weatherDayRealmList.add(weatherDayRealm);
                minTemp = 255;
                maxTemp = -255;
                humidity = 0;
                pressure = 0;
            }
        }
        return weatherDayRealmList;
    }

}

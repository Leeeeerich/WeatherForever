package com.guralnya.weatherforever.model.repository;

import android.util.Log;

import com.guralnya.weatherforever.model.client.WeatherAPI;
import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.WeatherWeek;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.Tools;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadWeather {

    private static IDownloadWeather mIDownloadWeather;

    public static void setIDownloadWeatherListener(IDownloadWeather IDownloadWeather) {
        mIDownloadWeather = IDownloadWeather;
    }

    public static void getWeatherTodayByCity(String city, String country) {
        getWeatherToday(city, country, null, null);
    }

    public static void getWeatherTodayByPosition(Double lat, Double lon) {
        getWeatherToday(null, null, lat, lon);
    }

    private static void getWeatherToday(String city,
                                        String country,
                                        Double lat,
                                        Double lon) {
        if (city != null) {
            Call<WeatherDay> call = WeatherAPI.getClient()
                    .create(WeatherAPI.ApiInterface.class)
                    .getToday(city + "," + country, Constants.UNITS, WeatherAPI.KEY);
            downloadWeatherToday(call);

        } else {
            Call<WeatherDay> call = WeatherAPI.getClient()
                    .create(WeatherAPI.ApiInterface.class)
                    .getToday(lat, lon, Constants.UNITS, WeatherAPI.KEY);
            downloadWeatherToday(call);
        }
    }

    private static void downloadWeatherToday(Call<WeatherDay> call) {
        // get weather for today
        call.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                Log.e(getClass().getName(), "onResponse");
                WeatherDay data = response.body();
                SessionRepository.setTodayForecast(data);
                if (mIDownloadWeather != null) {
                    mIDownloadWeather.getTodayForecastListener(data);
                }
            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {
                Log.e(getClass().getName(), "onFailure");
                Log.e(getClass().getName(), t.toString());
            }
        });
    }

    public static void getWeatherWeekByCity(String city, String country) {
        getWeatherWeek(city, country, null, null);
    }

    public static void getWeatherWeekByPosition(Double lat, Double lon) {
        getWeatherWeek(null, null, lat, lon);
    }

    private static void getWeatherWeek(String city,
                                       String country,
                                       Double lat,
                                       Double lon) {
        if (city != null) {
            Call<WeatherWeek> call = WeatherAPI.getClient()
                    .create(WeatherAPI.ApiInterface.class)
                    .getForecast(city + "," + country, Constants.UNITS, WeatherAPI.KEY);
            downloadWeatherWeek(call);
        } else {
            Call<WeatherWeek> call = WeatherAPI.getClient()
                    .create(WeatherAPI.ApiInterface.class)
                    .getForecast(lat, lon, Constants.UNITS, WeatherAPI.KEY);
            downloadWeatherWeek(call);
        }
    }

    private static void downloadWeatherWeek(Call<WeatherWeek> call) {
        // get weather week
        call.enqueue(new Callback<WeatherWeek>() {
            @Override
            public void onResponse(Call<WeatherWeek> call, Response<WeatherWeek> response) {
                Log.e(getClass().getName(), "onResponse");

                List<WeatherDay> data = response.body().getItems();
                Log.e("qwerty", "City = " + data.get(0).getCity() + "\nicon = " + data.get(0).getIcon());
                addOrUpdateWeatherWeekRep(Tools.hourlyForecastConvertToDaily(data));

            }

            @Override
            public void onFailure(Call<WeatherWeek> call, Throwable t) {
                Log.e(getClass().getName(), "onFailure");
                Log.e(getClass().getName(), t.toString());
            }
        });
    }

    private static void addOrUpdateWeatherWeekRep(List<WeatherDayRealm> data) {
        RealmConfiguration conf = Realm.getDefaultConfiguration();
        Realm realm = Realm.getInstance(conf);
        realm.beginTransaction();
        realm.insertOrUpdate(data);
        realm.commitTransaction();
    }

}

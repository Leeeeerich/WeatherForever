package com.guralnya.weatherforever.model.repository;

import android.util.Log;

import com.guralnya.weatherforever.model.client.CitiesApi;
import com.guralnya.weatherforever.model.objects.database_realm.CountriesRealm;
import com.guralnya.weatherforever.model.objects.dto.countries.CountriesDto;
import com.guralnya.weatherforever.model.objects.dto.countries.Result;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadCities {

    public static void getCountries() {
        Call<CountriesDto> call = CitiesApi.getClient()
                .create(CitiesApi.ApiInterface.class)
                .getCountries(CitiesApi.KEY, "ru");

        call.enqueue(new Callback<CountriesDto>() {
            @Override
            public void onResponse(Call<CountriesDto> call, Response<CountriesDto> response) {
                Log.e(getClass().getName(), "onResponse");
                addOrUpdateWeatherWeekRep(converter(response.body()));
            }

            @Override
            public void onFailure(Call<CountriesDto> call, Throwable t) {
                Log.e(getClass().getName(), "onFailure");
                Log.e(getClass().getName(), t.toString());
            }
        });
    }

    private static List<CountriesRealm> converter(CountriesDto data) {
        List<CountriesRealm> countriesRealmList = new ArrayList<>();
        for (Result result : data.getResult()) {
            countriesRealmList.add(new CountriesRealm(
                    result.getName(),
                    result.getIso(),
                    result.getIsoNumeric()));
        }
        return countriesRealmList;
    }

    private static void addOrUpdateWeatherWeekRep(List<CountriesRealm> data) {
        RealmConfiguration conf = Realm.getDefaultConfiguration();
        Realm realm = Realm.getInstance(conf);
        realm.beginTransaction();
        realm.insertOrUpdate(data);
        realm.commitTransaction();
    }

}

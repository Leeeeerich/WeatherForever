package com.guralnya.weatherforever.model.client;

import com.guralnya.weatherforever.model.objects.dto.countries.CountriesDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CitiesApi {
    public static final String BASE_URL = "http://geohelper.info/api/v1/";
    public static final String KEY = "qz066TZZJfV1MFohmuLSIe1d6mydeh1J";
    private static Retrofit retrofit = null;

    public interface ApiInterface {
        @GET("countries")
        Call<CountriesDto> getCountries(
                @Query("apiKey") String key,
                @Query("locale[lang]") String lang
        );

        @GET("regions")
        Call<List<CountriesDto>> getRegions(
                @Query("keyApi") String key,
                @Query("locale[lang]") String lang,
                @Query("filter[countryIso]") String filterCountries
        );

        @GET("cities")
        Call<List<CountriesDto>> getCities(
                @Query("keyApi") String key,
                @Query("locale[lang]") String lang,
                @Query("filter[regionId]") String regionId,
                @Query("filter[name]") String name
        );
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

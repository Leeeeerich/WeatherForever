package com.guralnya.weatherforever.model.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

public class WeatherWeek {
    @SerializedName("list")
    private List<WeatherDay> items;

    public WeatherWeek(List<WeatherDay> items) {
        this.items = items;
    }

    public List<WeatherDay> getItems() {
        return items;
    }
}

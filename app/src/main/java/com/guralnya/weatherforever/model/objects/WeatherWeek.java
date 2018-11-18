package com.guralnya.weatherforever.model.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

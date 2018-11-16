package com.guralnya.weatherforever.model.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

import io.realm.RealmObject;

public class WeatherDay {
    public class WeatherTemp {
        Double temp;
        Double pressure;
        Double humidity;
        Double temp_min;
        Double temp_max;
    }

    public class WeatherDescription {
        String icon;
    }

    public class WeatherWind {
        Double speed;
        Double deg;
    }

    @SerializedName("main")
    private WeatherTemp temp;

    @SerializedName("wind")
    private WeatherWind mWind;

    @SerializedName("weather")
    private List<WeatherDescription> desctiption;

    @SerializedName("name")
    private String city;

    @SerializedName("dt")
    private long timestamp;

    public WeatherDay(){}

    public WeatherDay(WeatherTemp temp, List<WeatherDescription> desctiption) {
        this.temp = temp;
        this.desctiption = desctiption;
    }
/*
    public Calendar getDate() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp * 1000);
        return date;
    }*/

    public long getTimestamp() {
        return timestamp;
    }

    public String getTemp() { return String.valueOf(temp.temp); }

    public String getPressure() {return String.valueOf(temp.pressure);}

    public String getHumidity() {return String.valueOf(temp.humidity);}

    public String getWindSpeed() {return String.valueOf(mWind.speed);}

    public String getTempMin() { return String.valueOf(temp.temp_min); }

    public String getTempMax() { return String.valueOf(temp.temp_max); }

    public String getTempInteger() { return String.valueOf(temp.temp.intValue()); }

    public String getTempWithDegree() { return String.valueOf(temp.temp.intValue()) + "\u00B0"; }

    public String getCity() { return city; }

    public String getIcon() { return desctiption.get(0).icon; }

    public String getIconUrl() {
        return "http://openweathermap.org/img/w/" + desctiption.get(0).icon + ".png";
    }
}

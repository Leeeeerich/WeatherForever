package com.guralnya.weatherforever.view.utils;

import android.graphics.drawable.Drawable;

import com.guralnya.weatherforever.BaseApplication;
import com.guralnya.weatherforever.R;

public class WeatherIconsDrawable {

    public static Drawable getIcWeather(String icon) {
        switch (icon) {
            case ("01d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_sun);
            }
            case ("01n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_sun);
            }
            case ("02d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_few_clouds);
            }
            case ("02n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_few_clouds);
            }
            case ("03d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_scattered_clouds);
            }
            case ("03n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_scattered_clouds);
            }
            case ("04n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_scattered_clouds);
            }
            case ("04d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_scattered_clouds);
            }
            case ("09d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_shower_rain);
            }
            case ("09n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_shower_rain);
            }
            case ("10d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_rain);
            }
            case ("10n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_rain);
            }
            case ("11d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_thunderstorm);
            }
            case ("11n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_thunderstorm);
            }
            case ("13d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_snow);
            }
            case ("13n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_snow);
            }
            case ("50n"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_mist);
            }
            case ("50d"): {
                return BaseApplication.context.getDrawable(R.drawable.ic_weather_mist);
            }
        }
        return BaseApplication.context.getDrawable(R.drawable.ic_weather_default);
    }

}

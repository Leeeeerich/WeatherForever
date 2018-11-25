package com.guralnya.weatherforever.view.utils;

import android.graphics.drawable.Drawable;

import com.guralnya.weatherforever.BaseApplication;
import com.guralnya.weatherforever.R;

public class WeatherIconsDrawable {

    private static final Drawable icWeatherRain = BaseApplication.context.getDrawable(R.drawable.ic_weather_rain);
    private static final Drawable icWeatherFewClouds = BaseApplication.context.getDrawable(R.drawable.ic_weather_few_clouds);
    private static final Drawable icWeatherMist = BaseApplication.context.getDrawable(R.drawable.ic_weather_mist);
    private static final Drawable icWeatherScattered = BaseApplication.context.getDrawable(R.drawable.ic_weather_scattered_clouds);
    private static final Drawable icWeatherSnow = BaseApplication.context.getDrawable(R.drawable.ic_weather_snow);
    private static final Drawable icWeatherSun = BaseApplication.context.getDrawable(R.drawable.ic_weather_sun);
    private static final Drawable icWeatherThunderstorm = BaseApplication.context.getDrawable(R.drawable.ic_weather_thunderstorm);
    private static final Drawable icWeatherShowerRain = BaseApplication.context.getDrawable(R.drawable.ic_weather_shower_rain);
    private static final Drawable icWeatherDefault = BaseApplication.context.getDrawable(R.drawable.ic_weather_default);

    public static Drawable getIcWeather(String icon) {
        switch (icon) {
            case ("01d"): {
                return icWeatherSun;
            }
            case ("01n"): {
                return icWeatherSun;
            }
            case ("02d"): {
                return icWeatherFewClouds;
            }
            case ("02n"): {
                return icWeatherFewClouds;
            }
            case ("03d"): {
                return icWeatherScattered;
            }
            case ("03n"): {
                return icWeatherScattered;
            }
            case ("04n"): {
                return icWeatherScattered;
            }
            case ("04d"): {
                return icWeatherScattered;
            }
            case ("09d"): {
                return icWeatherShowerRain;
            }
            case ("09n"): {
                return icWeatherShowerRain;
            }
            case ("10d"): {
                return icWeatherRain;
            }
            case ("10n"): {
                return icWeatherRain;
            }
            case ("11d"): {
                return icWeatherThunderstorm;
            }
            case ("11n"): {
                return icWeatherThunderstorm;
            }
            case ("13d"): {
                return icWeatherSnow;
            }
            case ("13n"): {
                return icWeatherSnow;
            }
            case ("50n"): {
                return icWeatherMist;
            }
            case ("50d"): {
                return icWeatherMist;
            }
        }
        return icWeatherDefault;
    }

}

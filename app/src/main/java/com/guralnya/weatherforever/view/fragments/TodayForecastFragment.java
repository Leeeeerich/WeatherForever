package com.guralnya.weatherforever.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;
import com.guralnya.weatherforever.presenters.ITodayForecastPresenter;
import com.guralnya.weatherforever.presenters.TodayForecastPresenter;
import com.guralnya.weatherforever.view.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import io.realm.Realm;

import static com.guralnya.weatherforever.utils.Constants.TIME_STAMP;

public class TodayForecastFragment extends Fragment implements ITodayForecastPresenter {

    @BindView(R.id.tvMinTemp)
    TextView tvMinTemp;
    @BindView(R.id.tvTempMax)
    TextView tvMaxTemp;
    @BindView(R.id.tvPressure)
    TextView tvPressure;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.tvWindSpeed)
    TextView tvWindSpeed;
    @BindView(R.id.imWeather)
    ImageView imWeather;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TodayForecastPresenter.getInstance().getTodayForecast();
        TodayForecastPresenter.getInstance().setITodayForecastListener(this);

        return view;
    }

    private void initView(WeatherDay weatherDay) {

        SimpleDateFormat dataFormat = new SimpleDateFormat("EE', 'dd MMMM", Locale.getDefault());
        Objects.requireNonNull(getActivity()).setTitle(dataFormat.format(weatherDay.getTimestamp() * 1000));

        tvMinTemp.setText(Tools.setPositiveSymbol(weatherDay.getTempMin()));
        tvMaxTemp.setText(Tools.setPositiveSymbol(weatherDay.getTempMax()));
        tvHumidity.setText(String.valueOf(weatherDay.getHumidity()).concat("%"));
        tvPressure.setText(
                Tools.pascalToMillimetersOfMercury(String.valueOf(weatherDay.getPressure()))
                        .concat(getString(R.string.mm)));
        tvWindSpeed.setText(weatherDay.getWindSpeed().concat(getString(R.string.wind_speed)));
        String imageURL = weatherDay.getIconUrl();
        Glide
                .with(getActivity())
                .load(imageURL)
                .into(imWeather);
    }

    @Override
    public void getForestTodayListener(WeatherDay weatherDay) {
        initView(weatherDay);
    }
}

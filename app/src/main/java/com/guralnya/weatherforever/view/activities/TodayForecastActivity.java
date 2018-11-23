package com.guralnya.weatherforever.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.presenters.ITodayForecastPresenter;
import com.guralnya.weatherforever.presenters.TodayForecastPresenter;
import com.guralnya.weatherforever.view.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodayForecastActivity extends AppCompatActivity implements ITodayForecastPresenter {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        TodayForecastPresenter.getInstance().setITodayForecastListener(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_today);
        ButterKnife.bind(this);

        TodayForecastPresenter.getInstance().getTodayForecast();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView(WeatherDay weatherDay) {
        try {
            tvMinTemp.setText(Tools.setPositiveSymbol(Tools.convertDoubleToInt(weatherDay.getTempMin())));
            tvMaxTemp.setText(Tools.setPositiveSymbol(Tools.convertDoubleToInt(weatherDay.getTempMax())));
            tvHumidity.setText(String.valueOf(weatherDay.getHumidity()).concat("%"));
            tvPressure.setText(
                    Tools.pascalToMillimetersOfMercury(String.valueOf(Math.round(weatherDay.getPressure())))
                            .concat(getString(R.string.mm)));
            tvWindSpeed.setText(weatherDay.getWindSpeed().concat(getString(R.string.wind_speed)));
            String imageURL = weatherDay.getIconUrl();
            Glide
                    .with(this)
                    .load(imageURL)
                    .into(imWeather);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getForestTodayListener(WeatherDay weatherDay) {
        initView(weatherDay);
    }
}

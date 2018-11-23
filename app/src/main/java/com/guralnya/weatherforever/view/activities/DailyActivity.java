package com.guralnya.weatherforever.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.view.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.guralnya.weatherforever.utils.Constants.TIME_STAMP;

public class DailyActivity extends AppCompatActivity {

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        ButterKnife.bind(this);

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

        Intent intent = getIntent();
        initView(intent.getLongExtra(Constants.TIME_STAMP, 0));
    }

    private void initView(Long timeStamp) {
        Realm realm = Realm.getDefaultInstance();
        WeatherDayRealm weatherDayRealm = realm.where(WeatherDayRealm.class)
                .equalTo(TIME_STAMP, timeStamp)
                .findFirst();

        SimpleDateFormat dataFormat = new SimpleDateFormat("EE', 'dd MMMM", Locale.getDefault());
        setTitle(dataFormat.format(weatherDayRealm.getTimeStamp() * 1000));

        tvMinTemp.setText(Tools.setPositiveSymbol(weatherDayRealm.getMinTemperature()));
        tvMaxTemp.setText(Tools.setPositiveSymbol(weatherDayRealm.getMaxTemperature()));
        tvHumidity.setText(weatherDayRealm.getHumidity().concat("%"));
        tvPressure.setText(
                Tools.pascalToMillimetersOfMercury(weatherDayRealm.getPressure())
                        .concat(getString(R.string.mm)));
        tvWindSpeed.setText(weatherDayRealm.getWindSpeed().concat(getString(R.string.wind_speed)));
        String imageURL = weatherDayRealm.getIconUrl();
        Glide
                .with(this)
                .load(imageURL)
                .into(imWeather);
    }
}

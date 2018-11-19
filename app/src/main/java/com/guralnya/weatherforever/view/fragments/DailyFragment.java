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
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;
import com.guralnya.weatherforever.view.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.guralnya.weatherforever.utils.Constants.TIME_STAMP;

public class DailyFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        ButterKnife.bind(this, view);

        initView(getArguments().getLong(TIME_STAMP));

        return view;
    }

    private void initView(Long timeStamp) {
        Realm realm = Realm.getDefaultInstance();
        WeatherDayRealm weatherDayRealm = realm.where(WeatherDayRealm.class)
                .equalTo(TIME_STAMP, timeStamp)
                .findFirst();

        SimpleDateFormat dataFormat = new SimpleDateFormat("EE', 'dd MMMM", Locale.getDefault());
        getActivity().setTitle(dataFormat.format(weatherDayRealm.getTimeStamp() * 1000));

        tvMinTemp.setText(Tools.setPositiveSymbol(weatherDayRealm.getMinTemperature()));
        tvMaxTemp.setText(Tools.setPositiveSymbol(weatherDayRealm.getMaxTemperature()));
        tvHumidity.setText(weatherDayRealm.getHumidity().concat("%"));
        tvPressure.setText(
                Tools.pascalToMillimetersOfMercury(weatherDayRealm.getPressure())
                        .concat(getString(R.string.mm)));
        tvWindSpeed.setText(weatherDayRealm.getWindSpeed().concat(getString(R.string.wind_speed)));
        String imageURL = weatherDayRealm.getIconUrl();
        Glide
                .with(getActivity())
                .load(imageURL)
                .into(imWeather);
    }

}

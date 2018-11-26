package com.guralnya.weatherforever.view.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.database_realm.CountriesRealm;
import com.guralnya.weatherforever.model.repository.DownloadWeather;
import com.guralnya.weatherforever.model.repository.IDownloadWeather;
import com.guralnya.weatherforever.model.repository.Repository;
import com.guralnya.weatherforever.model.repository.SessionRepository;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;
import com.guralnya.weatherforever.view.adapters.SpinnerCountriesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class SettingsActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 11;
    @BindView(R.id.switchUpdateWiFi)
    Switch mSwitchUpdateWiFi;
    @BindView(R.id.switchUpdateStartup)
    Switch mSwitchUpdateStartup;
    @BindView(R.id.switchAskLeaving)
    Switch mSwitchAskLeaving;
    @BindView(R.id.rgLocationSelection)
    RadioGroup mRadioGroup;

    @BindView(R.id.spinnerCountries)
    Spinner mSpinner;
    @BindView(R.id.etSetCity)
    EditText mSetCity;
    //@BindView(R.id.tvReload)
    TextView tvReload;

    private LocationManager mLocationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        ButterKnife.bind(this);
        tvReload = findViewById(R.id.tvReload);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        initView();
        DownloadWeather.setIDownloadWeatherListener(new IDownloadWeather() {
            @Override
            public void getTodayForecastListener(WeatherDay weatherDay) {
                try {
                    TextView tv = findViewById(R.id.tvCityByPosition);
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(weatherDay.getCity());
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COURSE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mRadioGroup.check(Constants.AUTO_LOCATION);
                }
            }
        }
    }

    private void initView() {
        spinnerCountriesInit();

        mSwitchUpdateWiFi.setChecked(SettingsManager.getUpdateOnlyByWifi(this));
        mSwitchUpdateWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsManager.setUpdateOnlyByWifi(getBaseContext(), isChecked);
            }
        });

        mSwitchUpdateStartup.setChecked(SettingsManager.getUpdateStartUp(this));
        mSwitchUpdateStartup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsManager.setUpdateStartUp(getBaseContext(), isChecked);
            }
        });

        mSwitchAskLeaving.setChecked(SettingsManager.getAskLeaving(this));
        mSwitchAskLeaving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsManager.setAskLeaving(getBaseContext(), isChecked);
            }
        });

        mRadioGroup.check(SettingsManager.getLocationSelection(this));
        chooseLocation();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SettingsManager.setLocationSelection(getBaseContext(), checkedId);
                chooseLocation();
            }
        });

        mSetCity.setText(SettingsManager.getWasSetCity(getBaseContext()));
        mSetCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SettingsManager.setCity(getBaseContext(), mSetCity.getText().toString());
                }
            }
        });

        tvReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsManager.setCountry(getBaseContext(), ((CountriesRealm) mSpinner.getSelectedItem()).getCountryIso());
                if (mSetCity != null && !mSetCity.getText().toString().equals("") && !mSetCity.getText().toString().equals(" "))
                    SettingsManager.setCity(getBaseContext(), mSetCity.getText().toString());
            }
        });
    }

    private void chooseLocation() {
        if (SettingsManager.getLocationSelection(this) == Constants.MANUAL_LOCATION) {
            mSpinner.setVisibility(View.VISIBLE);
            mSetCity.setVisibility(View.VISIBLE);
            tvReload.setVisibility(View.VISIBLE);
            try {
                findViewById(R.id.tvCityByPosition).setVisibility(View.GONE);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mSpinner.setVisibility(View.GONE);
            mSetCity.setVisibility(View.GONE);
            tvReload.setVisibility(View.GONE);
            try {
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            checkPermission();
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i(getClass().getName(), "true sdk >= 23");
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);
        }
        getLocation();
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.i(getClass().getName(), "get the coordinates ");
            mLocationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            assert mLocationManager != null;
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000 * 5, 0, locationListener);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000 * 10, 0, locationListener);
        } else {
            Log.e(getClass().getName(), "Not have permission");
            mRadioGroup.check(Constants.MANUAL_LOCATION);
            SettingsManager.setLocationSelection(this, Constants.MANUAL_LOCATION);
            chooseLocation();
        }
    }

    private void getCityNameByLocation(double lat, double lon) {
        try {
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadWeather.getWeatherTodayByPosition(lat, lon);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            Log.i(getClass().getName(), "Received phone coordinates \n lat: " + lat + "\n lng: " + lon);
            SessionRepository.setLatitude(lat);
            SessionRepository.setLongitude(lon);
            getCityNameByLocation(lat, lon);
            mLocationManager.removeUpdates(locationListener);
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };

    private void spinnerCountriesInit() {
        final RealmResults<CountriesRealm> countries = Repository.getCountries();
        final SpinnerCountriesAdapter adapter = new SpinnerCountriesAdapter(
                this, android.R.layout.simple_spinner_item, countries);

        if (countries != null) {
            mSpinner.setAdapter(adapter);
            mSpinner.setPrompt(SettingsManager.getWasSetCountry(getBaseContext()));
            countries.addChangeListener(new RealmChangeListener<RealmResults<CountriesRealm>>() {
                @Override
                public void onChange(@NonNull RealmResults<CountriesRealm> countriesRealms) {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}

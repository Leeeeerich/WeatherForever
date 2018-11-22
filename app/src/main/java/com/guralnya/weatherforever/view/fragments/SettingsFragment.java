package com.guralnya.weatherforever.view.fragments;

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
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.repository.SessionRepository;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LOCATION_SERVICE;

public class SettingsFragment extends Fragment {

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

    private LocationManager mLocationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView() {
        spinnerCountriesInit();

        mSwitchUpdateWiFi.setChecked(SettingsManager.getUpdateOnlyByWifi(getActivity()));
        mSwitchUpdateWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsManager.setUpdateOnlyByWifi(getActivity(), isChecked);
            }
        });

        mSwitchUpdateStartup.setChecked(SettingsManager.getUpdateStartUp(getActivity()));
        mSwitchUpdateStartup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsManager.setUpdateStartUp(getActivity(), isChecked);
            }
        });

        mSwitchAskLeaving.setChecked(SettingsManager.getAskLeaving(getActivity()));
        mSwitchAskLeaving.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsManager.setAskLeaving(getActivity(), isChecked);
            }
        });

        mRadioGroup.check(SettingsManager.getLocationSelection(getActivity()));
        chooseLocation();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SettingsManager.setLocationSelection(getActivity(), checkedId);
                chooseLocation();
            }
        });
    }

    private void chooseLocation() {
        if (SettingsManager.getLocationSelection(getActivity()) == Constants.MANUAL_LOCATION) {
            mSpinner.setVisibility(View.VISIBLE);
            mSetCity.setVisibility(View.VISIBLE);
        } else {
            mSpinner.setVisibility(View.GONE);
            mSetCity.setVisibility(View.GONE);
            checkPermission();
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i(getClass().getName(), "true sdk >= 23");
        }
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);
        }
        getLocation();
    }

    //
    private void getLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.i(getClass().getName(), "get the coordinates ");
            mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000 * 5, 0, locationListener);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000 * 10, 0, locationListener);
        } else {
            Log.e(getClass().getName(), "Not have permission");
            mRadioGroup.check(Constants.MANUAL_LOCATION);
            SettingsManager.setLocationSelection(getActivity(), Constants.MANUAL_LOCATION);
            chooseLocation();
        }

    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            Log.i(getClass().getName(), "Received phone coordinates \n lat: " + lat + "\n lng: " + lon);
            SessionRepository.setLatitude(lat);
            SessionRepository.setLongitude(lon);
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
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getActivity(),
                        R.array.countries,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        //spinner.setSelection(SettingsManager.getWasSetCountry(getActivity()));

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.countries);
                //SettingsManager.setCountry(getActivity(), choose[selectedItemPosition]);
                Log.i(getClass().getName(), "Checked = " + mSpinner.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}

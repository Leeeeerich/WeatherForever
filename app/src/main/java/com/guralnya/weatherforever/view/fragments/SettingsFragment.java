package com.guralnya.weatherforever.view.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.utils.SettingsManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends Fragment {

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

    private void chooseLocation(){
        if(SettingsManager.getLocationSelection(getActivity()) == R.id.rbManualLocation){
            mSpinner.setVisibility(View.VISIBLE);
            mSetCity.setVisibility(View.VISIBLE);
        } else {
            mSpinner.setVisibility(View.GONE);
            mSetCity.setVisibility(View.GONE);
        }
    }

    private void spinnerCountriesInit(){
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
                Log.e(getClass().getName(), "Checked = " + mSpinner.getSelectedItem());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}

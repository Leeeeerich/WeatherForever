package com.guralnya.weatherforever.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guralnya.weatherforever.R;

import butterknife.ButterKnife;

public class DailyFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        ButterKnife.bind(this, view);
        TextView minTemp = view.findViewById(R.id.tvMinTemp);
        TextView maxTemp = view.findViewById(R.id.tvTempMax);


        return view;
    }
}

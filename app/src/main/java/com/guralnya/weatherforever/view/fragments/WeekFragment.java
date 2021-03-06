package com.guralnya.weatherforever.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;
import com.guralnya.weatherforever.model.repository.Repository;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;
import com.guralnya.weatherforever.view.adapters.WeekFragmentRealmAdapter;

import java.util.Objects;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollection;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class WeekFragment extends Fragment {

    private static RecyclerView.Adapter mAdapter;

    private Realm mRealm;
    private RealmResults<WeatherDayRealm> mWeatherDayRealms;

    private static WeekFragment pInstance;
    public static WeekFragment getInstance() {

        if (pInstance == null) {
            pInstance = new WeekFragment();
        }
        return pInstance;
    }

    private static IWeekFragment mIWeekFragment;

    public void setIWeekFragment(IWeekFragment IWeekFragment) {
        mIWeekFragment = IWeekFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, container, false);

        mRealm = Realm.getDefaultInstance();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mWeatherDayRealms = mRealm.where(WeatherDayRealm.class).findAll();
        mWeatherDayRealms.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<WeatherDayRealm>>() {
            @Override
            public void onChange(RealmResults<WeatherDayRealm> weatherDayRealms, OrderedCollectionChangeSet changeSet) {
                mAdapter.notifyDataSetChanged();
            }
        });

        setAdapter(mRealm.where(WeatherDayRealm.class).findAll(), getActivity());

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        final SwipeRefreshLayout scrollView = view.findViewById(R.id.swipeRefreshLayout);
        scrollView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Repository.getWeather(getActivity());
                mAdapter.notifyDataSetChanged();
                scrollView.setRefreshing(false);
            }
        });

        return view;
    }

    private void setAdapter(OrderedRealmCollection<WeatherDayRealm> data, Context context) {
        mAdapter = new WeekFragmentRealmAdapter(data, context);
        ((WeekFragmentRealmAdapter) mAdapter).setClickItemListener(new WeekFragmentRealmAdapter.ClickItemListener() {
            @Override
            public void onClickItemListener(long timeStamp) {
                mIWeekFragment.clickItemListener(timeStamp);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle(R.string.weather_week);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    private RealmResults<WeatherDayRealm> getForecastFromDb(){
        if(SettingsManager.getLocationSelection(getActivity()) == Constants.MANUAL_LOCATION) {
            return mRealm.where(WeatherDayRealm.class).equalTo("mCity", SettingsManager.getWasSetCity(getActivity())).findAllAsync();
        }
        return mRealm.where(WeatherDayRealm.class).findAll();
    }
}

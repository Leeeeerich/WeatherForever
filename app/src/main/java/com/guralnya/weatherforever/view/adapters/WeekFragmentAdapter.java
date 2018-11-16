package com.guralnya.weatherforever.view.adapters;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.WeatherDay;
import com.guralnya.weatherforever.model.objects.WeatherWeek;

import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class WeekFragmentAdapter extends RecyclerView.Adapter<WeekFragmentAdapter.MyViewHolder> {

    private RealmList<WeatherDay> mWeatherDayRealmList;

    public WeekFragmentAdapter(RealmList<WeatherDay> mWeatherDayRealmList, String city){
        this.mWeatherDayRealmList = mWeatherDayRealmList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int typeView) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_weather, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        WeatherDay item = mWeatherDayRealmList.get(position);

        //myViewHolder.mDate.setText(item.getDate().compareTo(Calendar.getInstance()));
        myViewHolder.mTempature.setText(item.getTemp());
        //myViewHolder.mHumidity.setText(item.get);

    }

    @Override
    public int getItemCount() {
        return mWeatherDayRealmList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mWeather;
        private TextView mDate;
        private TextView mTempature;
        private TextView mHumidity;
        private CardView mCardView;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mWeather = itemView.findViewById(R.id.imWeather);
            mDate = itemView.findViewById(R.id.tvDate);
            mTempature = itemView.findViewById(R.id.tvTemperature);
            mHumidity = itemView.findViewById(R.id.tvHumidity);

            mCardView = itemView.findViewById(R.id.cardView);
        }
    }

}

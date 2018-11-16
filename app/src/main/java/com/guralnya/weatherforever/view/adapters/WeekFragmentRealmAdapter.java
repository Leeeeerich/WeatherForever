package com.guralnya.weatherforever.view.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;


public class WeekFragmentRealmAdapter extends RealmRecyclerViewAdapter<WeatherDayRealm,
        WeekFragmentRealmAdapter.MyViewHolder> {

    private boolean inDeletionMode = false;
    private Set<Integer> countersToDelete = new HashSet<>();

    public WeekFragmentRealmAdapter(@Nullable OrderedRealmCollection<WeatherDayRealm> data,
                                    boolean autoUpdate) {
        super(data, autoUpdate);
        setHasStableIds(true);
    }

    void enableDeletionMode(boolean enabled) {
        inDeletionMode = enabled;
        if (!enabled) {
            countersToDelete.clear();
        }
        notifyDataSetChanged();
    }

    Set<Integer> getCountersToDelete() {
        return countersToDelete;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.card_weather,
                viewGroup,
                false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final WeatherDayRealm item = getItem(position);

        //myViewHolder.mDate.setText();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mWeather;
        private TextView mDate;
        private TextView mTempature;
        private TextView mHumidity;
        private CardView mCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mWeather = itemView.findViewById(R.id.imWeather);
            mDate = itemView.findViewById(R.id.tvDate);
            mTempature = itemView.findViewById(R.id.tvTemperature);
            mHumidity = itemView.findViewById(R.id.tvHumidity);

            mCardView = itemView.findViewById(R.id.cardView);
        }
    }
}

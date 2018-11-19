package com.guralnya.weatherforever.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.database_realm.WeatherDayRealm;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.view.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class WeekFragmentRealmAdapter extends RealmRecyclerViewAdapter<WeatherDayRealm,
        WeekFragmentRealmAdapter.MyViewHolder> {

    private Context mContext;

    public WeekFragmentRealmAdapter(@Nullable OrderedRealmCollection<WeatherDayRealm> data,
                                    Context context) {
        super(data, true);
        setHasStableIds(true);
        this.mContext = context;
    }

    public interface ClickItemListener {
        void onClickItemListener(long timeStamp);
    }

    private ClickItemListener mClickItemListener;

    public void setClickItemListener(ClickItemListener clickItemListener) {
        mClickItemListener = clickItemListener;
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

        SimpleDateFormat dataFormat = new SimpleDateFormat("EEEE', 'dd MMMM", Locale.getDefault());
        myViewHolder.mDate.setText(dataFormat.format(item.getTimeStamp() * 1000));

        myViewHolder.mTemperature.setText(
                Tools.setPositiveSymbol(item.getMinTemperature()).concat(", " +
                        Tools.setPositiveSymbol(item.getMaxTemperature())));
        myViewHolder.mHumidity.setText(item.getHumidity().concat("%"));

        String imageURL = item.getIconUrl();
        Glide
                .with(mContext)
                .load(imageURL)
                .into(myViewHolder.mWeather);

        myViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemListener.onClickItemListener(item.getTimeStamp());
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mWeather;
        private TextView mDate;
        private TextView mTemperature;
        private TextView mHumidity;
        private CardView mCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mWeather = itemView.findViewById(R.id.imWeather);
            mDate = itemView.findViewById(R.id.tvDate);
            mTemperature = itemView.findViewById(R.id.tvTemperature);
            mHumidity = itemView.findViewById(R.id.tvHumidity);

            mCardView = itemView.findViewById(R.id.cardView);
        }
    }
}

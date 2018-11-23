package com.guralnya.weatherforever.view.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.model.objects.database_realm.CountriesRealm;

import java.util.List;

public class SpinnerCountriesAdapter extends ArrayAdapter<CountriesRealm> {

    private Activity context;
    private List<CountriesRealm> data;

    public SpinnerCountriesAdapter(Activity context, int resource, List<CountriesRealm> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.item_spinner_country, parent, false);
        }

        ViewHolder viewHolder;

        CountriesRealm item = data.get(position);

        if (item != null) {
            viewHolder = new ViewHolder();
            viewHolder.myCountry = (TextView) row.findViewById(R.id.spinnerItem);
            row.setTag(item.getCountryName());
            if (viewHolder.myCountry != null) {
                viewHolder.myCountry.setText(item.getCountryName());
                viewHolder.myCountry.setTextSize(18);
                viewHolder.myCountry.setPadding(0, 5, 0, 0);
            }
        }

        return row;
    }

    private class ViewHolder {
        private TextView myCountry;
    }


}

package com.guralnya.weatherforever.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.utils.SettingsManager;
import com.guralnya.weatherforever.view.fragments.IWeekFragment;
import com.guralnya.weatherforever.view.fragments.SettingsFragment;
import com.guralnya.weatherforever.view.fragments.WeekFragment;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IWeekFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemBackgroundResource(R.color.transparent);


        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_week);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_main, WeekFragment.getInstance())
                    .commit();
        }

        WeekFragment.getInstance().setIWeekFragment(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_today) {
         /*   getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, new TodayForecastFragment(), Constants.TODAY_FORECAST_FRAGMENT)
                    .commit();*/
            Intent intent = new Intent(this, TodayForecastActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_week) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, WeekFragment.getInstance(), Constants.WEEK_FORECAST_FRAGMENT)
                    .commit();
        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, new SettingsFragment(), Constants.SETTINGS_FRAGMENT)
                    .commit();
        } else if (id == R.id.nav_exit) {
            if (SettingsManager.getAskLeaving(this)) {
                askExitAlertDialog();
            } else {
                finish();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void askExitAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.ask_exit)
                .setMessage(getResources().getString(R.string.ask_exit_text))
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void clickItemListener(long timeStamp) {
        Intent intent = new Intent(this, DailyActivity.class);
        intent.putExtra(Constants.TIME_STAMP, timeStamp);
        startActivity(intent);
    }

    public Drawable loadImageFromAsset() {
        try {
            InputStream ims = getAssets().open("logo.png");
            Log.e(getClass().getName(), "Image");
            return Drawable.createFromStream(ims, null);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

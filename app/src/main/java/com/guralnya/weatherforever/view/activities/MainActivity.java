package com.guralnya.weatherforever.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.guralnya.weatherforever.R;
import com.guralnya.weatherforever.utils.Constants;
import com.guralnya.weatherforever.view.fragments.DailyFragment;
import com.guralnya.weatherforever.view.fragments.IWeekFragment;
import com.guralnya.weatherforever.view.fragments.SettingsFragment;
import com.guralnya.weatherforever.view.fragments.WeekFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IWeekFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_main, WeekFragment.getInstance())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_today) {
            getSupportFragmentManager().beginTransaction()
                    //.add(R.id.content_main, HomeFragment.newInstance(null))
                    .commit();
        } else if (id == R.id.nav_week) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, WeekFragment.getInstance(), Constants.WEEK_FORECAST_FRAGMENT)
                    .commit();
        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, new SettingsFragment(), Constants.SETTINGS_FRAGMENT)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void clickItemListener(long timeStamp) {
        Intent intent = new Intent(this, DailyActivity.class);
        intent.putExtra(Constants.TIME_STAMP, timeStamp);
        startActivity(intent);

        /*
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.TIME_STAMP, timeStamp);
        Fragment f = new DailyFragment();
        f.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, f)
                .addToBackStack(Constants.DAILY_FORECAST)
                .commit();*/
    }
}

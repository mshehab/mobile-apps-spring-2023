package com.example.hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.HttpUrl;

public class MainActivity extends AppCompatActivity implements CitiesFragment.CitiesListener, WeatherFragment.WeatherListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new CitiesFragment())
                .commit();

    }




    @Override
    public void onCitySelected(DataService.City city) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, WeatherFragment.newInstance(city) )
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToForecastFragment(DataService.City city) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ForecastFragment.newInstance(city) )
                .addToBackStack(null)
                .commit();
    }
}
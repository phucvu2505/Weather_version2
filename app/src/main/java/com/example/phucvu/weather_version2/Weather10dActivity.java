package com.example.phucvu.weather_version2;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.phucvu.weather_version2.adapter.Forecast10dAdapter;
import com.example.phucvu.weather_version2.asyncTask.Weather10dAsyncTask;
import com.example.phucvu.weather_version2.entity.forecastWeather10d.WeatherFC10d;
import com.google.gson.Gson;

/**
 * Created by phucvu on 11/13/16.
 */

public class Weather10dActivity extends AppCompatActivity {

    private Location currentLocation;
    private Weather10dAsyncTask weather10dAsyncTask;
    private RecyclerView recyclerView;
    private Forecast10dAdapter forecast10dAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_fc10d_main);

        context = this;
        Intent intent = getIntent();
        double[] locationData = intent.getExtras().getDoubleArray(MainActivity.LOCATION_DATA);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tlb_weather_forecast10d);
        toolbar.setTitle(getResources().getString(R.string.daily_forecast));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.list_forecast_10d);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        weather10dAsyncTask = new Weather10dAsyncTask(this, locationData);
        weather10dAsyncTask.execute();
        weather10dAsyncTask.setCallBack(new Weather10dAsyncTask.CallBack() {
            @Override
            public void onFinish(String body) {
                Gson gson = new Gson();
                WeatherFC10d weatherFC10d = gson.fromJson(body, WeatherFC10d.class);
                forecast10dAdapter = new Forecast10dAdapter(context, weatherFC10d.getList());
                recyclerView.setAdapter(forecast10dAdapter);
            }
        });
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_toolbars_fc10d, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

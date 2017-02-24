package com.example.admin.realtimeweather;

import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.realtimeweather.data.Channel;
import com.example.admin.realtimeweather.data.Item;
import com.example.admin.realtimeweather.service.WeatherServiceCallback;
import com.example.admin.realtimeweather.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        this.temperatureTextView = temperatureTextView;
        this.conditionTextView = conditionTextView;
        this.locationTextView = locationTextView;
        this.dialog = dialog;


        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);


        service = new YahooWeatherService(this);
        //service.refreshWeather("Canada");

        //dialog.setMessage("Loading...");
        //dialog.show();

        service.refreshWeather("Austin, TX");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        this.dialog = dialog;
        dialog.hide();

        Item item = channel.getItem();

        temperatureTextView.setText(item.getCondition().getTemp() + "\u00B0" + channel.getUnits().getTemp());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception exception) {
        this.dialog = dialog;
        //dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}

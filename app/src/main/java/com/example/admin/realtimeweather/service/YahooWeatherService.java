package com.example.admin.realtimeweather.service;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.acl.LastOwnerException;

import com.example.admin.realtimeweather.data.Channel;

/**
 * Created by Admin on 12/25/2016.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;


    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        this.location = location;
        return location;
    }

    public void refreshWeather(String s){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                Channel channel = new Channel();
                String unit = "c";
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='" + unit + "'", location);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    connection.setUseCaches(false);
                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while (((line = reader.readLine()) != null)) {
                        result.append(line);
                    }
                    reader.close();

                    JSONObject data = new JSONObject(result.toString());
                    JSONObject queryResults= data.optJSONObject("query");
                    JSONObject channelJSON = queryResults.getJSONObject("results").optJSONObject("channel");
                    channel.populate(channelJSON);
                    callback.serviceSuccess(channel);

                } catch (Exception e) {
                    error = e;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s){
                if(s==null && error != null){
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResults= data.optJSONObject("query");
                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.execute(location);

    }
    public class LocationWeatherException extends Exception {
        public LocationWeatherException(String detailmsg){
            super(detailmsg);
        }
    }

}



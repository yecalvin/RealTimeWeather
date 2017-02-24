package com.example.admin.realtimeweather.service;

import com.example.admin.realtimeweather.data.Channel;

/**
 * Created by Admin on 12/25/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}

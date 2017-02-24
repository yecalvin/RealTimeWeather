package com.example.admin.realtimeweather.data;

import org.json.JSONObject;

/**
 * Created by Admin on 12/25/2016.
 */
public class Units implements JSONPopulator {
    private String temp;

    public String getTemp() {
        return temp;
    }

    @Override
    public void populate(JSONObject data) {
        temp = data.optString("temperature");

    }
}

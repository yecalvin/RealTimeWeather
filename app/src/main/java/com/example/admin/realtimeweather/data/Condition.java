package com.example.admin.realtimeweather.data;

import org.json.JSONObject;

/**
 * Created by Admin on 12/25/2016.
 */
public class Condition implements JSONPopulator {
    private int code;
    private int temp;
    private String description;

    public String getDescription() {
        return description;
    }

    public int getTemp() {

        return temp;
    }

    public int getCode() {

        return code;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temp = data.optInt("temp");
        description = data.optString("text");
    }
}

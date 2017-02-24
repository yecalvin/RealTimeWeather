package com.example.admin.realtimeweather.data;

import org.json.JSONObject;

/**
 * Created by Admin on 12/25/2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}

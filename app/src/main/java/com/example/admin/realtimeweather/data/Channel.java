package com.example.admin.realtimeweather.data;

import org.json.JSONObject;

/**
 * Created by Admin on 12/25/2016.
 */
public class Channel implements JSONPopulator {

    public Item item;
    public Units units;
    public String location;

    public String getLocation() {
        return location;
    }

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        JSONObject locationData = data.optJSONObject("location");

        String region = locationData.optString("region");
        String country = locationData.optString("country");

        location = String.format("%s,%s", locationData.optString("city"),
                (region.length() != 0 ? region : country));
    }

}

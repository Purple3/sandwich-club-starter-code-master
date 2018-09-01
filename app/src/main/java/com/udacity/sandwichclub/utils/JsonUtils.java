package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String jsonString) throws JSONException  {
        // int json1 = Log.e("json", json);
        Sandwich sandwichDetails = new Sandwich();

        JSONObject sandwichJson = new JSONObject(jsonString);
        JSONObject nameJson = sandwichJson.getJSONObject("name");
        String mainName = nameJson.getString("mainName");
        //Log.e("name",nameJson.toString());


        String origin = sandwichJson.getString("placeOfOrigin");

        sandwichDetails.setMainName(mainName);
        sandwichDetails.setPlaceOfOrigin(origin);

        return sandwichDetails;
    }
}

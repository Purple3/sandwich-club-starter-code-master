package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String jsonString) throws JSONException  {

        Sandwich sandwichDetails = new Sandwich();

        JSONObject sandwichJson = new JSONObject(jsonString);
        JSONObject nameJson = sandwichJson.getJSONObject("name");

        String mainName = nameJson.getString("mainName");
        JSONArray arrJson = nameJson.getJSONArray("alsoKnownAs");

        ArrayList<String> alsoKnownAsList = new ArrayList<String>();
        for(int i = 0; i < arrJson.length(); i++)
            alsoKnownAsList.add(arrJson.getString(i));

        String origin = sandwichJson.getString("placeOfOrigin");
        String description = sandwichJson.getString("description");
        String image = sandwichJson.getString("image");

        JSONArray ingredientsJsonArray = sandwichJson.getJSONArray("ingredients");
        ArrayList<String> ingredientsList = new ArrayList<String>();
        for(int i = 0; i < ingredientsJsonArray.length(); i++)
            ingredientsList.add(ingredientsJsonArray.getString(i));

        sandwichDetails.setMainName(mainName);
        sandwichDetails.setAlsoKnownAs(alsoKnownAsList);
        sandwichDetails.setPlaceOfOrigin(origin);
        sandwichDetails.setDescription(description);
        sandwichDetails.setImage(image);
        sandwichDetails.setIngredients(ingredientsList);

        return sandwichDetails;
    }
}

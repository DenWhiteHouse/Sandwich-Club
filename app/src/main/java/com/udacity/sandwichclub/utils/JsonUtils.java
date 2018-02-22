package com.udacity.sandwichclub.utils;
//Needed for manipulating Json Objects
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

public class JsonUtils {

    public JsonUtils() throws JSONException {
        //Excepetion on JsonParsing
    }

    public static Sandwich parseSandwichJson(String json) {

        try {
        //create the JSON object to get string data as defined by the Sandwich model
        JSONObject rootObject = new JSONObject(json);
        JSONObject subObj = rootObject.getJSONObject("name");
        String mainName = subObj.getString("mainName");
        JSONArray akaArray = subObj.getJSONArray("alsoKnownAs");
        String placeOfOrigin = rootObject.getString("placeOfOrigin");
        String description = rootObject.getString("description");
        String imagePath = rootObject.getString("image");

        JSONArray ingredientArray = rootObject.getJSONArray("ingredients");
        List<String> alsoKnownAsList = new ArrayList<>();

        //Getting akaArray
        for (int i = 0; i < akaArray.length(); i++) {
            String alsoKnownAs = akaArray.getString(i);
            alsoKnownAsList.add(alsoKnownAs);
        }

        // Getting ingredientList
        List<String> ingredientList = new ArrayList<>();
        for (int i = 0; i < ingredientArray.length(); i++) {
            ingredientList.add(ingredientArray.getString(i));
        }

        Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, imagePath, ingredientList);
        return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

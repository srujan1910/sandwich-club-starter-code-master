package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            // JSON object formation from the json string
            JSONObject baseObject = new JSONObject(json);
            JSONObject name = baseObject.getJSONObject("name");

            // Getting Sandwich name
            String mainName = name.optString("mainName");

            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            ArrayList<String> AKAlist = new ArrayList<>();

            for(int i = 0; i< alsoKnownAs.length(); i++ ) {
                AKAlist.add(alsoKnownAs.getString(i));
            }

            // Getting Place of Origin
            String placeOfOrigin = baseObject.getString("placeOfOrigin");
            // Getting Description
            String description = baseObject.getString("description");
            // Getting image
            String image = baseObject.getString("image");

            // Creation of JSON Array to extract Array objects

            JSONArray ingredients = baseObject.getJSONArray("ingredients");
            //ArrayList<String> AKAlist = new ArrayList<>();
            ArrayList<String> ingrelist = new ArrayList<>();

            for(int j = 0; j< ingredients.length(); j++ ) {
                ingrelist.add(ingredients.getString(j));
            }


            return new Sandwich (mainName,AKAlist,placeOfOrigin,description,image,ingrelist);



} catch (JSONException e) {
        e.printStackTrace();

        return null;
        }


    }
}

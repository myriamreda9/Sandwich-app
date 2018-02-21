package com.udacity.sandwichclub.utils;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        ArrayList AlsoKnownlist = new ArrayList();
        ArrayList IngredList = new ArrayList();
        String Image , MainName , PlaceOfOrigin, Description;
        try {

            JSONObject SndObject = new JSONObject(json);
            JSONObject nameObject = SndObject.getJSONObject("name");
            MainName = nameObject.getString("mainName");
            sandwich.setMainName(MainName); // main Name
            JSONArray AlsoKnown = nameObject.getJSONArray("alsoKnownAs");

            // Also Known
            if (AlsoKnown != null ){
            for (int i = 0; i < AlsoKnown.length(); i++) {
                String also = AlsoKnown.getString(i);
                AlsoKnownlist.add(also);
            }
           }
            sandwich.setAlsoKnownAs(AlsoKnownlist);
           // Place of Origin
            PlaceOfOrigin = SndObject.getString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(PlaceOfOrigin);
           // Description
            Description = SndObject.getString("description");
            sandwich.setDescription(Description);
            // Set the Image
            Image = SndObject.getString("image");
            sandwich.setImage(Image);

            JSONArray Ingredients = SndObject.getJSONArray("ingredients");
            //Ingredients
            if (Ingredients != null){
            for (int i =0 ; i<Ingredients.length();i++){
                IngredList.add(Ingredients.getString(i));
            }
            }
            sandwich.setIngredients(IngredList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}

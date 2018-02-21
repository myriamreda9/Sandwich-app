package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.NetworkUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.udacity.sandwichclub.utils.NetworkUtils.getImageFromHttpUrl;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView DescriptionView ;
    private TextView AlsoKnownView ;
    private TextView PlaceOfOriginView ;
    private TextView IngredientsView ;
    private ImageView ingredientsIv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DescriptionView = findViewById(R.id.description_tv);
        AlsoKnownView = findViewById(R.id.also_known_tv);
        PlaceOfOriginView = findViewById(R.id.origin_tv);
        IngredientsView = findViewById(R.id.ingredients_tv);
        ingredientsIv =  findViewById(R.id.image_iv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }


        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);


        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        DescriptionView.setText(sandwich.getDescription());
        StringBuilder ingredients = new StringBuilder();
        for (String Ing : sandwich.getIngredients()) {
            ingredients.append(Ing + ",");
        }
        IngredientsView.setText(ingredients);
        StringBuilder AlsoKnown = new StringBuilder();
        for (String AK : sandwich.getAlsoKnownAs()) {
            AlsoKnown.append(AK + ",");
        }
        AlsoKnownView.setText(AlsoKnown);

        PlaceOfOriginView.setText(sandwich.getPlaceOfOrigin());



    }

//    private class SandwichImages extends AsyncTask<String, Void, Bitmap> {
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            Bitmap SImage = null;
//            try {
//                SImage = NetworkUtils.getImageFromHttpUrl(strings[0]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return SImage;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap image) {
//            ingredientsIv.setImageBitmap(image);
//        }
//    }
}

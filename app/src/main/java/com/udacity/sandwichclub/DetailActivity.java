package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_tv);

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

        ImageView imageView =  findViewById(R.id.image_tv);
        TextView AKAlist = findViewById(R.id.also_known_tv);
        TextView origin = findViewById(R.id.origin_tv);
        TextView description = findViewById(R.id.description_tv);
        TextView ingrelist = findViewById(R.id.ingredients_tv);

        /*if (!(sandwich.getImage().isEmpty())) {
            imageView.setImageAlpha();
        }*/

        if (!(sandwich.getPlaceOfOrigin().isEmpty())) {
            origin.setText(sandwich.getPlaceOfOrigin());
        }

        if (!(sandwich.getDescription().isEmpty())) {
            description.setText(sandwich.getDescription());
        }

        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        if (!(alsoKnownAs.isEmpty())) {
                String result = "";
            for (String s : alsoKnownAs) {
                result = result + s + ", ";
            }

            if (result.length() > 0) {
                result = result.substring(0, result.length() - 2);
            }
            AKAlist.setText(result);

            }

       List<String> ingredients = sandwich.getIngredients();
        if (!(ingredients.isEmpty())) {
            String result1 = "";
            for (String s : ingredients) {
                result1 = result1 + s + "\n";
            }
            ingrelist.setText(result1);
        }

        }

}

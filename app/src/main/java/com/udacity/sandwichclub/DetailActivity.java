package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageIv = findViewById(R.id.image_iv);

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
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView alsoKnowAsTv = findViewById(R.id.also_known_as_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);

        TextView ingredientsIv = findViewById(R.id.ingredients_tv);
        TextView originTv = findViewById(R.id.origin_tv);


        originTv.setText(sandwich.getPlaceOfOrigin());
        descriptionTv.setText(sandwich.getDescription());

        StringBuilder otherNames = new StringBuilder();
        int numOfOtherNames = 0;
        for(String otherName :  sandwich.getAlsoKnownAs() ) {
            numOfOtherNames++;
            if(numOfOtherNames < sandwich.getAlsoKnownAs().size()) {
                otherNames.append(otherName).append(", ");
            }
        }
        alsoKnowAsTv.setText(otherNames);


        StringBuilder ingredients = new StringBuilder();
        int numOfIngredients = 0;
        for(String ingredient :  sandwich.getIngredients() ) {
            numOfIngredients++;
            if(numOfIngredients < sandwich.getIngredients().size()){
                ingredients.append(ingredient).append(", ");
            }


        }
        ingredientsIv.setText(ingredients);
    }
}

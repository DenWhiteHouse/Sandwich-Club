package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
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
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //Basic way to bind views and objects to be populated on UI
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);
        TextView placeoforginTv = findViewById(R.id.origin_tv);
        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        //SetText to the binded TextViews
        ingredientsTv.setText(sandwich.getIngredients().toString());

        //as there are Emtpy Akas is better to check before and in case tell the user
        String akaString=sandwich.getAlsoKnownAs().toString();
        if (akaString.equals("[]")){
            String nullAka= getResources().getString(R.string.no_aka);
            alsoKnownAsTv.setText(nullAka);
        }
        else{
            alsoKnownAsTv.setText(akaString);
        }
        descriptionTv.setText(sandwich.getDescription());
        placeoforginTv.setText(sandwich.getPlaceOfOrigin());
    }
}

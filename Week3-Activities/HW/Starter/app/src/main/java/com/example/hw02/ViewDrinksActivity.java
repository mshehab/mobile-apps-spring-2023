package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewDrinksActivity extends AppCompatActivity {
    TextView textViewDrinksCount, textViewDrinkSize, textViewDrinkAlcoholPercent, textViewDrinkAddedOn;
    ImageView imageViewPrevious, imageViewNext, imageViewDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drinks);

        textViewDrinksCount = findViewById(R.id.textViewDrinksCount);
        textViewDrinkSize = findViewById(R.id.textViewDrinkSize);
        textViewDrinkAlcoholPercent = findViewById(R.id.textViewDrinkAlcoholPercent);
        textViewDrinkAddedOn = findViewById(R.id.textViewDrinkAddedOn);
        imageViewPrevious = findViewById(R.id.imageViewPrevious);
        imageViewNext = findViewById(R.id.imageViewNext);
        imageViewDelete = findViewById(R.id.imageViewDelete);

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
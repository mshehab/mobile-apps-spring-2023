package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewDrinksActivity extends AppCompatActivity {
    TextView textViewDrinksCount, textViewDrinkSize, textViewDrinkAlcoholPercent, textViewDrinkAddedOn;
    ImageView imageViewPrevious, imageViewNext, imageViewDelete;
    ArrayList<Drink> drinks = new ArrayList<>();
    int currentIndex = 0;
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

        if(getIntent() != null && getIntent().hasExtra(MainActivity.DRINKS_KEY)){
            drinks = (ArrayList<Drink>) getIntent().getSerializableExtra(MainActivity.DRINKS_KEY);
            currentIndex = 0;
            displayCurrentDrink();
        }


        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.DRINKS_KEY, drinks);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex == drinks.size() - 1){
                    currentIndex = 0;
                } else {
                    currentIndex = currentIndex + 1;
                }
                displayCurrentDrink();
            }
        });

        imageViewPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrevious();
            }
        });

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drink drink = drinks.get(currentIndex);
                drinks.remove(drink);

                if(drinks.size() == 0){
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.DRINKS_KEY, drinks);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                   showPrevious();
                }
            }
        });
    }

    private void showPrevious(){
        if(currentIndex == 0){
            currentIndex = drinks.size() - 1;
        } else {
            currentIndex = currentIndex - 1;
        }
        displayCurrentDrink();
    }

    private void displayCurrentDrink(){
        Drink drink = drinks.get(currentIndex);
        textViewDrinksCount.setText("Drink " + (currentIndex + 1) + " out of " + drinks.size());
        textViewDrinkSize.setText(drink.getSize() + " oz");
        textViewDrinkAlcoholPercent.setText(drink.getAlcohol() + "% Alcohol");
        textViewDrinkAddedOn.setText(drink.getAddedOn().toString());
    }

}
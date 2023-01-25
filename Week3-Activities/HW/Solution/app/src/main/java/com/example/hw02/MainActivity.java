package com.example.hw02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textViewNoDrinks, textViewBACLevel, textViewStatus, textViewWeightGender;
    View viewStatus;
    Profile profile;
    Button buttonAddDrink;

    ArrayList<Drink> drinks = new ArrayList<>();
    public static final String DRINKS_KEY = "DRINKS";

    private ActivityResultLauncher<Intent> startGetProfileForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        profile = (Profile) result.getData().getSerializableExtra(SetProfileActivity.PROFILE_KEY);
                        textViewWeightGender.setText(profile.getWeight() + "Lbs (" + profile.getGender() + ")" );
                        buttonAddDrink.setEnabled(true);
                    } else {
                        profile = null;
                        textViewWeightGender.setText("N/A");
                        drinks.clear();
                        setupBACInfo();
                        buttonAddDrink.setEnabled(false);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> startAddDrinkForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Drink drink = (Drink) result.getData().getSerializableExtra(AddDrinkActivity.DRINK_KEY);
                        drinks.add(drink);
                        setupBACInfo();
                    } else {

                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> startViewDrinksForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        drinks = (ArrayList<Drink>) result.getData().getSerializableExtra(DRINKS_KEY);
                        setupBACInfo();
                    } else {

                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNoDrinks = findViewById(R.id.textViewNoDrinks);
        textViewBACLevel = findViewById(R.id.textViewBACLevel);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewWeightGender = findViewById(R.id.textViewWeightGender);
        viewStatus = findViewById(R.id.viewStatus);

        buttonAddDrink = findViewById(R.id.buttonAddDrink);
        buttonAddDrink.setEnabled(false);

        setTitle("BAC Calculator");
        findViewById(R.id.buttonSetProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetProfileActivity.class);
                startGetProfileForResult.launch(intent);
            }
        });

        findViewById(R.id.buttonViewDrinks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drinks.size() > 0){
                    Intent intent = new Intent(MainActivity.this, ViewDrinksActivity.class);
                    intent.putExtra(DRINKS_KEY, drinks);
                    startViewDrinksForResult.launch(intent);
                } else {
                    Toast.makeText(MainActivity.this, "You have no drinks to view !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.buttonAddDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDrinkActivity.class);
                startAddDrinkForResult.launch(intent);
            }
        });

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinks.clear();
                profile = null;
                textViewWeightGender.setText("N/A");
                setupBACInfo();
                buttonAddDrink.setEnabled(false);
            }
        });
    }

    private void setupBACInfo(){
        textViewNoDrinks.setText("# Drinks: " + drinks.size());

        if(drinks.size() == 0){
            textViewBACLevel.setText("BAC Level: 0.000");
            textViewStatus.setText("You're safe");
            viewStatus.setBackgroundResource(R.color.safe_color);
        } else {
            double valueA = 0.0;
            double valueR = 0.66;
            if(profile.getGender() == "Male"){
                valueR = 0.73;
            }

            for (Drink drink: drinks) {
                valueA = valueA + drink.getAlcohol() * drink.getSize() / 100.0;
            }

            double bac = valueA*5.14/(profile.getWeight() * valueR);

            textViewBACLevel.setText("BAC Level: " + String.format("%.3f", bac));

            if(bac <= 0.08){
                textViewStatus.setText("You're safe");
                viewStatus.setBackgroundResource(R.color.safe_color);
            } else if(bac <= 0.2){
                textViewStatus.setText("Be careful");
                viewStatus.setBackgroundResource(R.color.becareful_color);
            } else {
                textViewStatus.setText("Over Limit");
                viewStatus.setBackgroundResource(R.color.overlimit_color);
            }
        }
    }
}

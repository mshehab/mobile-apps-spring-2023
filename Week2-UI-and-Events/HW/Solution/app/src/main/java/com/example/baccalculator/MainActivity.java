package com.example.baccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextWeight;
    RadioGroup radioGroupGender, radioGroupDrinkSize;
    TextView textViewWeight, textViewAlcoholPercentage, textViewNoDrinks, textViewBACLevel, textViewStatus;
    SeekBar seekBarAlcohol;
    View viewStatus;

    Profile profile;
    ArrayList<Drink> drinks = new ArrayList<>();


    //buttonSetWeight
    //buttonReset
    //buttonAddDrink

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupDrinkSize = findViewById(R.id.radioGroupDrinkSize);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewAlcoholPercentage = findViewById(R.id.textViewAlcoholPercentage);
        textViewNoDrinks = findViewById(R.id.textViewNoDrinks);
        textViewBACLevel = findViewById(R.id.textViewBACLevel);
        textViewStatus = findViewById(R.id.textViewStatus);
        seekBarAlcohol = findViewById(R.id.seekBarAlcohol);
        viewStatus = findViewById(R.id.viewStatus);

        findViewById(R.id.buttonSetWeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredWeight = editTextWeight.getText().toString();
                try{
                    double weight = Double.valueOf(enteredWeight);

                    if(weight > 0 ){
                        String gender = "Female";
                        if(radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale){
                            gender = "Male";
                        }
                        profile = new Profile(gender, weight);
                        //Display the entered weight and gender as shown in Fig 1(c).
                        // Clear the weight EditText.
                        textViewWeight.setText(String.valueOf(weight) + " lbs (" + gender + ")" );
                        editTextWeight.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Enter a valid weight !!", Toast.LENGTH_SHORT).show();
                    }
                    drinks.clear();
                    calculateBAC();
                    // Clear any of the previously added drinks, clear the BAC and UI should be as shown in Fig 1(c).
                } catch (NumberFormatException ex){
                    Toast.makeText(MainActivity.this, "Enter a valid weight !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekBarAlcohol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAlcoholPercentage.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.buttonAddDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profile == null){
                    Toast.makeText(MainActivity.this, "Setup your weight and gender!!", Toast.LENGTH_SHORT).show();
                } else {
                    double size = 1;
                    if(radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton5oz){
                        size = 5;
                    } else if(radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton12oz){
                        size = 12;
                    }

                    double percentage = seekBarAlcohol.getProgress();
                    if(percentage > 0 ){
                        Drink drink = new Drink(size, percentage);
                        drinks.add(drink);
                        calculateBAC();
                    } else {
                        Toast.makeText(MainActivity.this, "Setup the Alcohol %!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile = null;
                textViewWeight.setText("");
                drinks.clear();
                calculateBAC();

                radioGroupGender.check(R.id.radioButtonFemale);
                editTextWeight.setText("");

                radioGroupDrinkSize.check(R.id.radioButton1oz);
                seekBarAlcohol.setProgress(12);


            }
        });
    }

    void calculateBAC(){
        textViewNoDrinks.setText("# Drinks: " + String.valueOf(drinks.size()));

        if(drinks.size() == 0){
            textViewBACLevel.setText("BAC Level: 0.000");
            textViewStatus.setText("You're Safe");
            viewStatus.setBackgroundColor(getColor(R.color.safe_color));
        } else {
            double valueA = 0.0;

            for (Drink drink: drinks) {
                valueA = valueA + drink.getSize() * drink.getPercentage() / 100.0;
            }

            double valueR = 0.73;
            if(profile.getGender() == "Female"){
                valueR = 0.66;
            }

            double bac = valueA * 5.14 / (profile.getWeight() * valueR);
            textViewBACLevel.setText("BAC Level: " + String.format("%.3f", bac));

            //String.format("%,.2f", d)

            if(bac <= 0.08){
                textViewStatus.setText("You're Safe");
                viewStatus.setBackgroundColor(getColor(R.color.safe_color));
            } else if(bac <= 0.2){
                textViewStatus.setText("Be careful");
                viewStatus.setBackgroundColor(getColor(R.color.becareful_color));
            } else {
                textViewStatus.setText("Over the limit!");
                viewStatus.setBackgroundColor(getColor(R.color.overlimit_color));
            }

        }
    }
}
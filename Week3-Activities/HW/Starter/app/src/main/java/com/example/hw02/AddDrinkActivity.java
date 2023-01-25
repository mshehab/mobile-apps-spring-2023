package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddDrinkActivity extends AppCompatActivity {
    RadioGroup radioGroupDrinkSize;
    TextView textViewAlcoholPercentage;
    SeekBar seekBarAlcohol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);

        radioGroupDrinkSize = findViewById(R.id.radioGroupDrinkSize);
        textViewAlcoholPercentage = findViewById(R.id.textViewAlcoholPercentage);
        seekBarAlcohol = findViewById(R.id.seekBarAlcohol);


        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.buttonAddDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
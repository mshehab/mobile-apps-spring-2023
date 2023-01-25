package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddDrinkActivity extends AppCompatActivity {
    RadioGroup radioGroupDrinkSize;
    TextView textViewAlcoholPercentage;
    SeekBar seekBarAlcohol;
    public static final String DRINK_KEY = "DRINK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);

        radioGroupDrinkSize = findViewById(R.id.radioGroupDrinkSize);
        textViewAlcoholPercentage = findViewById(R.id.textViewAlcoholPercentage);
        seekBarAlcohol = findViewById(R.id.seekBarAlcohol);

        setTitle("Add Drink");

        ((SeekBar)findViewById(R.id.seekBarAlcohol)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.buttonAddDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int alcohol = seekBarAlcohol.getProgress();
                double size = 1.0;

                if(radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton5oz){
                    size = 5.0;
                } else if(radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton12oz){
                    size = 12.0;
                }

                if(alcohol > 0){
                    Drink drink = new Drink(alcohol, size);
                    Intent intent = new Intent();
                    intent.putExtra(DRINK_KEY, drink);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddDrinkActivity.this, "Alcohol should be greater than 0%", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
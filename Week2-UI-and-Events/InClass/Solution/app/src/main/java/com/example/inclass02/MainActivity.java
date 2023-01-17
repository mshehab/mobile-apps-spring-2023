package com.example.inclass02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumberDecimal;
    TextView textViewDiscountPercentage;
    TextView textViewDiscountedPrice;

    public static final String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        textViewDiscountPercentage = findViewById(R.id.textViewDiscountPercentage);
        textViewDiscountedPrice = findViewById(R.id.textViewDiscountedPrice);

        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumberDecimal.setText("");
                textViewDiscountedPrice.setText("");
                textViewDiscountPercentage.setText("");
            }
        });

        findViewById(R.id.button5Percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndShowDiscount(5.0);
            }
        });

        findViewById(R.id.button10Percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndShowDiscount(10.0);
            }
        });

        findViewById(R.id.button15Percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndShowDiscount(15.0);
            }
        });

        findViewById(R.id.button20Percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndShowDiscount(20.0);
            }
        });

        findViewById(R.id.button50Percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndShowDiscount(50.0);
            }
        });

    }

    private void calculateAndShowDiscount(double discount){
        String enteredAmount = editTextNumberDecimal.getText().toString();
        try{
            double amount = Double.valueOf(enteredAmount);
            double discountedPrice = (100 - discount) * amount / 100.0;
            textViewDiscountPercentage.setText(String.valueOf(discount) + "%");
            textViewDiscountedPrice.setText(String.valueOf(discountedPrice));
        } catch (NumberFormatException exception){
            Toast.makeText(MainActivity.this, "Enter a valid number !!", Toast.LENGTH_SHORT).show();
        }
    }
}
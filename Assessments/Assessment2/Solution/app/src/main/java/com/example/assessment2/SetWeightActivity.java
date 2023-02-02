package com.example.assessment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.assessment2.databinding.ActivitySetWeightBinding;

public class SetWeightActivity extends AppCompatActivity {

    public static final String WEIGHT_KEY = "weight";

    ActivitySetWeightBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetWeightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Set Weight");


        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Double weight = Double.valueOf(binding.editTextWeight.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra(WEIGHT_KEY, weight);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException ex){
                    Toast.makeText(SetWeightActivity.this, "Enter valid weight", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
package com.example.assessment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.assessment2.databinding.ActivitySetGenderBinding;

public class SetGenderActivity extends AppCompatActivity {

    public static final String GENDER_KEY = "gender";

    ActivitySetGenderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetGenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Set Gender");

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gender = "Female";
                if(binding.radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale){
                    gender = "Male";
                }

                Intent intent = new Intent();
                intent.putExtra(GENDER_KEY, gender);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
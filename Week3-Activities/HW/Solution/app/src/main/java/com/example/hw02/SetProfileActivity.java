package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SetProfileActivity extends AppCompatActivity {
    EditText editTextWeight;
    RadioGroup radioGroupGender;
    public static final String PROFILE_KEY = "profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        editTextWeight = findViewById(R.id.editTextWeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        findViewById(R.id.buttonSetWeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gender = "Female";
                if(radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale){
                    gender = "Male";
                }

                try{
                    double weight = Double.valueOf(editTextWeight.getText().toString());
                    if(weight > 0){
                        Profile profile = new Profile(weight, gender);
                        Intent intent = new Intent();
                        intent.putExtra(PROFILE_KEY, profile);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(SetProfileActivity.this, "Enter a valid weight", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException ex){
                    Toast.makeText(SetProfileActivity.this, "Enter a valid weight", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setTitle("Set Weight/Gender");

    }
}
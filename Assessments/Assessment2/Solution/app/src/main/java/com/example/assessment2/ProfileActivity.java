package com.example.assessment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.assessment2.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    public static final String PROFILE_KEY = "profile";

    ActivityProfileBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Profile");

        if(getIntent() != null && getIntent().hasExtra(PROFILE_KEY)){
            Profile profile = (Profile) getIntent().getSerializableExtra(PROFILE_KEY);
            binding.textViewGender.setText(profile.getGender());
            binding.textViewWeight.setText(String.valueOf(profile.getWeight()) + " lbs");
        }

        binding.buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
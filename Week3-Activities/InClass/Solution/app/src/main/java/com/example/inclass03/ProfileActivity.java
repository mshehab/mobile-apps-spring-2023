package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView textViewName, textViewEmail, textViewId, textViewDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewId = findViewById(R.id.textViewId);
        textViewDept = findViewById(R.id.textViewDept);

        if(getIntent() != null && getIntent().hasExtra(RegistrationActivity.KEY_PROFILE)){
            Profile profile = (Profile) getIntent().getSerializableExtra(RegistrationActivity.KEY_PROFILE);

            textViewName.setText(profile.getName());
            textViewEmail.setText(profile.getEmail());
            textViewDept.setText(profile.getDepartment());
            textViewId.setText(profile.getId());

        }

        setTitle("Profile");
    }
}
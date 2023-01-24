package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class DepartmentActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    public static final String KEY_DEPT = "DEPT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.buttonSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedItemId = radioGroup.getCheckedRadioButtonId();
                String department = "Computer Science";
                if(selectedItemId == R.id.radioButtonSIS){
                    department = "Software and Info Systems";
                } else if(selectedItemId == R.id.radioButtonBio){
                    department = "Bio Informatics";
                } else if(selectedItemId == R.id.radioButtonDS){
                    department = "Data Science";
                }

                Intent intent = new Intent();
                intent.putExtra(KEY_DEPT, department);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
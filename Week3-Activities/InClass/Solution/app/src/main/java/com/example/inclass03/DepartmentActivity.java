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

        setTitle("Department");
        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.buttonSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                String dept = getString(R.string.cs_label);
                if(selectedId == R.id.radioButtonCS){
                    dept = getString(R.string.cs_label);
                } else if(selectedId == R.id.radioButtonSIS){
                    dept = getString(R.string.sis_label);
                } else if(selectedId == R.id.radioButtonBIO){
                    dept = getString(R.string.bio_label);
                } else if(selectedId == R.id.radioButtonDS){
                    dept = getString(R.string.ds_label);
                }

                Intent intent = new Intent();
                intent.putExtra(KEY_DEPT, dept);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
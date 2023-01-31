package com.example.assessment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textViewOperation, textViewAnswer;
    EditText editTextA, editTextB;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewOperation = findViewById(R.id.textViewOperation);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        editTextA = findViewById(R.id.editTextA);
        editTextB = findViewById(R.id.editTextB);
        radioGroup = findViewById(R.id.radioGroup);

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    double num_a = Double.valueOf(editTextA.getText().toString());
                    try{
                        double num_b = Double.valueOf(editTextB.getText().toString());

                        int selectedId = radioGroup.getCheckedRadioButtonId();

                        if(selectedId == R.id.radioButtonAdd){
                            textViewAnswer.setText(String.format("%.2f", num_a + num_b));
                            textViewOperation.setText("+");
                        } else if(selectedId == R.id.radioButtonSub){
                            textViewAnswer.setText(String.format("%.2f", num_a - num_b));
                            textViewOperation.setText("-");

                        } else if(selectedId == R.id.radioButtonMul){
                            textViewAnswer.setText(String.format("%.2f", num_a * num_b));
                            textViewOperation.setText("x");

                        } else if(selectedId == R.id.radioButtonDiv){
                            textViewOperation.setText("/");

                            if(num_b == 0){
                                Toast.makeText(MainActivity.this, "Cannot Divide by ZERO !!", Toast.LENGTH_SHORT).show();
                            } else {
                                double result = num_a/num_b;
                                textViewAnswer.setText(String.format("%.2f", result));
                            }
                        }
                    } catch (NumberFormatException ex){
                        Toast.makeText(MainActivity.this, "Enter a valid number B", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException ex){
                    Toast.makeText(MainActivity.this, "Enter a valid number A", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextA.setText("");
                editTextB.setText("");
                textViewAnswer.setText("N/A");
            }
        });

    }
}
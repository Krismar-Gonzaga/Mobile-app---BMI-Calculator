package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button calculate;
    EditText weightInput, heightInput;
    EditText bmiResult;
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weightInput = (EditText)findViewById(R.id.weightInput);
        heightInput = (EditText) findViewById(R.id.heightInput);
        calculate = (Button) findViewById(R.id.calculate);
        bmiResult = findViewById(R.id.bmiResult);
        category = findViewById(R.id.category);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isfilled = fieldChecker();
                if (isfilled) {
                    CalculateProcess();
                }
            }
        });

    }

    public boolean fieldChecker(){
        String weightInputValue = weightInput.getText().toString().trim();
        String heightInputvalue = heightInput.getText().toString().trim();

        if(weightInputValue.isEmpty() || heightInputvalue.isEmpty()){
            Toast.makeText(this, "Weight and Height are required!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public float calculateBmi(String weightInput, String heightInput){
        Float result = Float.valueOf(weightInput) / (Float.valueOf(heightInput) * Float.valueOf(heightInput));
        return result;
    }

    public void CalculateProcess(){
        if (fieldChecker()){
            String weightInputValue = weightInput.getText().toString().trim();
            String heightInputvalue = heightInput.getText().toString().trim();
            float bmi = calculateBmi(weightInputValue, heightInputvalue);
            String status = "";
            if (bmi < 15)
                status = "Very severely underweight";
            else if (bmi < 16)
                status = "Severely underweight";
            else if (bmi < 18.5)
                status = "Underweight";
            else if (bmi < 25)
                status = "Normal";
            else if (bmi < 30)
                status = "Overweight";
            else if (bmi < 35)
                status = "Obese Class 1 - Moderately Obese";
            else if (bmi < 40)
                status = "Obese Class 2 - Severely Obese";
            else
                status = "Obese Class 3 - Very Severely Obese";
            bmiResult.setText(String.format("%.2f", bmi));
            category.setText(status);
            cleardata();
        }
    }

    public void cleardata(){
        weightInput.setText("");
        heightInput.setText("");
    }
}
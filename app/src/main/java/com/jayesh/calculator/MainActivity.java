package com.jayesh.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double answer = 0;
    double num1,num2;
    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText input1 = findViewById(R.id.input1);
        EditText input2 = findViewById(R.id.input2);
        TextView operationText = findViewById(R.id.operationText);
        TextView resultText = findViewById(R.id.resultText);
        Button addBtn = findViewById(R.id.addBtn);
        Button subtractBtn = findViewById(R.id.subtractBtn);
        Button divideBtn = findViewById(R.id.divideBtn);
        Button multiplyBtn = findViewById(R.id.multiplyBtn);
        Button calculateBtn = findViewById(R.id.calculateBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationText.setText("+");
                operation = "add";
            }
        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationText.setText("-");
                operation = "subtract";
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationText.setText("X");
                operation = "multiply";
            }
        });

        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationText.setText("/");
                operation = "divide";
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = input1.getText().toString();
                String num2 = input2.getText().toString();
                if (num1.isEmpty() || num2.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter Values first",Toast.LENGTH_SHORT).show();
                }
                else if(operation == ""){
                    Toast.makeText(getApplicationContext(),"Choose an operation",Toast.LENGTH_SHORT).show();
                }
                else {
                    double a = Double.parseDouble(num1);
                    double b = Double.parseDouble(num2);
                    switch (operation){
                        case "add":
                            answer = a+b;
                            break;
                        case "subtract":
                            answer = a-b;
                            break;
                        case "multiply":
                            answer = a*b;
                            break;
                        case "divide":
                            answer = a/b;
                            break;
                    }
                    Toast.makeText(getApplicationContext(),"Result is " + String.valueOf(answer),Toast.LENGTH_SHORT).show();
                    resultText.setText(String.valueOf(answer));
                    answer = 0;
                }
            }
        });
    }
}
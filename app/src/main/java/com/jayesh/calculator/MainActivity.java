package com.jayesh.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnDot};
    final int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
    final int [] keyButtons = { R.id.btnClear, R.id.btnDelete, R.id.btnEqual};
    private double operand1,operand2;
    private Boolean operatorSelected = false;
    String result = "";
    TextView txtScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtScreen = findViewById(R.id.txtScreen);
        setNumerics();
        setOperators();
        setKeys();
    }

    private void setNumerics(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!result.equals(""))
                {
                    clear();
                }
                else {
                    Button button = (Button) v;
                    txtScreen.setText(txtScreen.getText() + "" + (button.getText()));
                }
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperators(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!result.equals(""))
                {
                    clear();
                }
                else if(!operatorSelected && !txtScreen.getText().toString().equals("")) {
                    operand1 = Double.parseDouble(txtScreen.getText().toString());
                    Button button = (Button) v;
                    txtScreen.setText(txtScreen.getText() + " " + (button.getText()) + " ");
                    operatorSelected = true;
                }
                else{
                    if(operatorSelected) {
                        Toast.makeText(getApplicationContext(), "You have already selected an operator", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please enter an operand first", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setKeys(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!result.equals(""))
                {
                    clear();
                }
                else {
                    Button button = (Button) v;
                    switch (button.getText().toString()) {
                        case "Delete":
                            delete();
                            break;
                        case "=":
                            calculate();
                            break;
                        case "C":
                            clear();
                    }
                }
            }
        };
        for (int id : keyButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void calculate(){
        String[] arrOfTxtScreen = txtScreen.getText().toString().split(" ");
        if(arrOfTxtScreen.length < 3){
            Toast.makeText(getApplicationContext(), "Please enter operands and operators first", Toast.LENGTH_SHORT).show();
        }
        else{
            operand2 = Double.parseDouble(arrOfTxtScreen[2]);
            switch (arrOfTxtScreen[1]){
                case "+":
                    add();
                    break;
                case "-":
                    subtract();
                    break;
                case "*":
                    multiply();
                    break;
                case "/":
                    divide();
                    break;
            }
            txtScreen.setText(txtScreen.getText() + " = " + result);
            Toast.makeText(getApplicationContext(),"Result is " + result,Toast.LENGTH_SHORT).show();
        }
    }

    private void add(){
        result = String.valueOf(operand1 + operand2);
    }

    private void subtract(){
        result = String.valueOf(operand1 - operand2);
    }

    private void multiply(){
        result = String.valueOf(operand1 * operand2);
    }

    private void divide(){
        result = String.valueOf(operand1 / operand2);
    }

    private void clear(){
        txtScreen.setText("");
        operatorSelected = false;
        operand2 = 0.0d;
        operand1 = 0.0d;
        result = "";
        Toast.makeText(getApplicationContext(),"Cleared, please enter values to continue",Toast.LENGTH_SHORT).show();
    }

    private void delete(){
        String[] arrOfTxtScreen = txtScreen.getText().toString().split(" ");
        switch (arrOfTxtScreen.length){
            case 3:
                txtScreen.setText(txtScreen.getText().toString().substring(0, txtScreen.getText().toString().length() - 1));
                break;
            case 2:
                txtScreen.setText(txtScreen.getText().toString().substring(0, txtScreen.getText().toString().length() - 3));
                operatorSelected = false;
                break;
            case 1:
                if(!txtScreen.getText().toString().equals("")){
                    txtScreen.setText(txtScreen.getText().toString().substring(0, txtScreen.getText().toString().length() - 1));
                    operand1 = 0.0d;
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nothing to delete",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
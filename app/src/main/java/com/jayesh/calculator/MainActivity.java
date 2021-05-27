package com.jayesh.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double operand1, operand2;
    private boolean operatorSelected = false;
    private TextView txtScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtScreen = findViewById(R.id.txtScreen);
        setNumerics();
        setOperators();
        setKeys();
    }

    private void setNumerics() {
        final int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour,
                R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnDot};
        View.OnClickListener listener = new View.OnClickListener() {
            String text;

            @Override
            public void onClick(View v) {
                if (txtScreen.getText().toString().split(" ").length > 3) {
                    clear();
                }
                Button button = (Button) v;
                text = txtScreen.getText() + "" + (button.getText());
                txtScreen.setText(text);
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperators() {
        final int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
        View.OnClickListener listener = new View.OnClickListener() {
            String text;

            @Override
            public void onClick(View v) {
                if (txtScreen.getText().toString().split(" ").length > 3) {
                    clear();
                }
                if (!operatorSelected && txtScreen.getText().toString().length() > 0) {

                    if (!isNumeric(txtScreen.getText().toString())) {
                        text = "0";
                        txtScreen.setText(text);
                    }
                    operand1 = Double.parseDouble(txtScreen.getText().toString());
                    Button button = (Button) v;
                    text = txtScreen.getText() + " " + (button.getText()) + " ";
                    txtScreen.setText(text);
                    operatorSelected = true;
                } else {
                    if (operatorSelected) {
                        Toast.makeText(getApplicationContext(), "You have already selected an operator", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter an operand first", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setKeys() {
        final int[] keyButtons = {R.id.btnClear, R.id.btnDelete, R.id.btnEqual};
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtScreen.getText().toString().split(" ").length > 3) {
                    clear();
                }
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
        };
        for (int id : keyButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void calculate() {
        String text;
        String[] arrOfTxtScreen = txtScreen.getText().toString().split(" ");
        if (arrOfTxtScreen.length < 3) {
            Toast.makeText(getApplicationContext(), "Please enter operands and operators first", Toast.LENGTH_SHORT).show();
        } else {
            if (!isNumeric(arrOfTxtScreen[2])) {
                arrOfTxtScreen[2] = "0";
                text = arrOfTxtScreen[0] + " " + arrOfTxtScreen[1] + " " +arrOfTxtScreen[2];
                txtScreen.setText(text);
            }
            operand2 = Double.parseDouble(arrOfTxtScreen[2]);
            switch (arrOfTxtScreen[1]) {
                case "+":
                    showResult(operand1 + operand2);
                    break;
                case "-":
                    showResult(operand1 - operand2);
                    break;
                case "*":
                    showResult(operand1 * operand2);
                    break;
                case "/":
                    showResult(operand1 / operand2);
                    break;
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void showResult(double result) {
        String text;
        if (result == (long) result) {
            text = txtScreen.getText() + " = " + (long) result;
            Toast.makeText(getApplicationContext(), "Result is " + (long) result, Toast.LENGTH_SHORT).show();
        } else {
            text = txtScreen.getText() + " = " + (long) result;
            Toast.makeText(getApplicationContext(), "Result is " + result, Toast.LENGTH_SHORT).show();
        }
        txtScreen.setText(text);
    }

    private void clear() {
        txtScreen.setText("");
        operatorSelected = false;
        operand2 = 0.0d;
        operand1 = 0.0d;
        Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
    }

    private void delete() {
        String text;
        String[] arrOfTxtScreen = txtScreen.getText().toString().split(" ");
        switch (arrOfTxtScreen.length) {
            case 3:
                text = txtScreen.getText().toString().substring(0, txtScreen.getText().toString().length() - 1);
                txtScreen.setText(text);
                break;
            case 2:
                text = txtScreen.getText().toString().substring(0, txtScreen.getText().toString().length() - 3);
                txtScreen.setText(text);
                operatorSelected = false;
                break;
            case 1:
                if (txtScreen.getText().toString().length() > 0) {
                    text = txtScreen.getText().toString().substring(0, txtScreen.getText().toString().length() - 1);
                    txtScreen.setText(text);
                    operand1 = 0.0d;
                } else {
                    Toast.makeText(getApplicationContext(), "Nothing to delete", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
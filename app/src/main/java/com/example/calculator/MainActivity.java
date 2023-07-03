package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double firstNum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView appUse = findViewById(R.id.appuse);

        Button on = findViewById(R.id.on);
        on.setOnClickListener(view -> {
            appUse.setVisibility(View.VISIBLE);
            appUse.setText("0");
            resetCalculator();
        });

        Button ac = findViewById(R.id.ac);
        ac.setOnClickListener(view -> {
            appUse.setText("0");
            resetCalculator();
        });

        ArrayList<Button> numberButtons = new ArrayList<>();
        numberButtons.add(findViewById(R.id.n0));
        numberButtons.add(findViewById(R.id.n1));
        numberButtons.add(findViewById(R.id.n2));
        numberButtons.add(findViewById(R.id.n3));
        numberButtons.add(findViewById(R.id.n4));
        numberButtons.add(findViewById(R.id.n5));
        numberButtons.add(findViewById(R.id.n6));
        numberButtons.add(findViewById(R.id.n7));
        numberButtons.add(findViewById(R.id.n8));
        numberButtons.add(findViewById(R.id.n9));

        for (Button button : numberButtons) {
            button.setOnClickListener(view -> {
                String currentText = appUse.getText().toString();
                if (currentText.equals("0")) {
                    appUse.setText(button.getText().toString());
                } else {
                    appUse.append(button.getText().toString());
                }
            });
        }

        ArrayList<Button> operatorButtons = new ArrayList<>();
        operatorButtons.add(findViewById(R.id.div));
        operatorButtons.add(findViewById(R.id.mul));
        operatorButtons.add(findViewById(R.id.add));
        operatorButtons.add(findViewById(R.id.sub));
        operatorButtons.add(findViewById(R.id.mod));

        for (Button button : operatorButtons) {
            button.setOnClickListener(view -> {
                firstNum = Double.parseDouble(appUse.getText().toString());
                operation = button.getText().toString();
                appUse.setText("0");
            });
        }

        Button dot = findViewById(R.id.dot);
        dot.setOnClickListener(view -> {
            String currentText = appUse.getText().toString();
            if (!currentText.contains(".")) {
                appUse.append(".");
            }
        });

        Button del = findViewById(R.id.del);
        del.setOnClickListener(view -> {
            String currentText = appUse.getText().toString();
            if (currentText.length() > 1) {
                appUse.setText(currentText.substring(0, currentText.length() - 1));
            } else if (!currentText.equals("0")) {
                appUse.setText("0");
            }
        });

        Button equal = findViewById(R.id.equal);
        equal.setOnClickListener(view -> {
            double secondNum = Double.parseDouble(appUse.getText().toString());
            double result;
            switch (operation) {
                case "/":
                    result = firstNum / secondNum;
                    break;
                case "x":
                    result = firstNum * secondNum;
                    break;
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "%":
                    result = firstNum % secondNum;
                    break;
                default:
                    result = firstNum;
            }
            appUse.setText(String.valueOf(result));
            resetCalculator();
        });
    }

    private void resetCalculator() {
        firstNum = 0;
        operation = "";
    }
}


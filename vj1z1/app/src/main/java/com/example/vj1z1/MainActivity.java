package com.example.vj1z1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Adler32;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText num1, num2, operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button2);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        operator = findViewById(R.id.operator);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                if (num2.getText().toString().equals("0") && operator.getText().toString().equals("/")) {
                    Toast error = Toast.makeText(context, "Can't divide with 0", duration);
                    error.show();
                } else {
                    float result = calculate(num1.getText().toString(), num2.getText().toString(), operator.getText().toString());
                    Toast toast = Toast.makeText(context, String.valueOf(result), duration);
                    toast.show();
                }
            }
        });

    }

    private float calculate(String num1, String num2, String operator) {
        float a = Float.parseFloat(num1);
        float b = Float.parseFloat(num2);
        float result = 0;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }

        return result;
    }

}
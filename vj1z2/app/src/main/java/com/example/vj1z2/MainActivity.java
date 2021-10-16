package com.example.vj1z2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button button;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = spinner.getSelectedItem().toString();
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                if (num2.getText().toString().equals("0") && selected.equals("/")) {
                    Toast error = Toast.makeText(context, "Can't divide with 0", duration);
                    error.show();
                } else {
                    float result = calculate(num1.getText().toString(), num2.getText().toString(), selected);
                    sendMessage(result);
                }
            }
        });
    }

    private void sendMessage(float result) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("msg", String.valueOf(result));
        startActivity(intent);
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
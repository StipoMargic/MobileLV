package com.example.vj1z2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    TextView txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activirty_main2);

        txt = findViewById(R.id.textView);
        Intent intent = getIntent();
        txt.setText(intent.getStringExtra("msg"));
    }
}

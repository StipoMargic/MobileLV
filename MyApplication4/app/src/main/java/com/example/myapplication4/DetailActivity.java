package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication4.entity.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    Button updateBtn, deleteBtn;
    EditText title, column;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "Note").build();
        title = findViewById(R.id.textView5);
        column = findViewById(R.id.textView6);
        updateBtn = findViewById(R.id.button2);
        deleteBtn = findViewById(R.id.button3);

        if (getIntent().hasExtra("selectedNote")){
            Note note = getIntent().getParcelableExtra("selectedNote");

            title.setText(note.getTitle());
            column.setText(note.getColumn());

            btnClicked(note);
        }
    }

    private void btnClicked(Note note) {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        note.setTitle(title.getText().toString());
                        note.setColumn(column.getText().toString());
                        db.noteDAO().update(note);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(DetailActivity.this).setTitle("Delete note?")
                        .setPositiveButton("Yes", null).setNegativeButton("No", null).show();

                Button positiveBtn = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                db.noteDAO().destroy(note);

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}
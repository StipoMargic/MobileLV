package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication4.entity.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.microedition.khronos.egl.EGLDisplay;

public class MainActivity2 extends AppCompatActivity {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    Database db;
    EditText title, column;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "Note").build();
        title = findViewById(R.id.textView3);
        column = findViewById(R.id.textView4);
        floatingActionButton = findViewById(R.id.button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteTitle = title.getText().toString();
                String noteColumn = column.getText().toString();

                if (!TextUtils.isEmpty(noteTitle) || !TextUtils.isEmpty(noteColumn)) {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.noteDAO().create(new Note(noteTitle, noteColumn));

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(MainActivity2.this, "Err", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
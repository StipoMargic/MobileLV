package com.example.myapplication4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication4.entity.Note;
import com.example.myapplication4.entity.NoteDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements CustomViewHolder.ClickListener {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    Database db;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = Room.databaseBuilder(getApplicationContext(), Database.class, "Note").allowMainThreadQueries().build();

        executeService();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });
    }

    private void executeService() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = db.noteDAO().findAll();
                adapter = new CustomAdapter (notes, MainActivity.this::onItemClick);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void addNote() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, View v) {
        List<Note>  notes= db.noteDAO().findAll();
        Note selectedNote= notes.get(position);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("selectedNote", selectedNote);
        startActivity(intent);
    }
}
package com.example.myapplication4;

import androidx.room.RoomDatabase;

import com.example.myapplication4.entity.Note;
import com.example.myapplication4.entity.NoteDAO;

@androidx.room.Database(entities = Note.class, version = 1)
public abstract class Database extends RoomDatabase{
    public abstract NoteDAO noteDAO();
}

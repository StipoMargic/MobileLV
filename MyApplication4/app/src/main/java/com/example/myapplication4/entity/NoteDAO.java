package com.example.myapplication4.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM Note")
    List<Note> findAll();

    @Insert
    void create(Note...notes);

    @Update(entity =  Note.class)
    void update(Note note);

    @Delete
    void destroy(Note note);
}

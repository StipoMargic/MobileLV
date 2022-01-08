package com.example.vjezba6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditFieldActivity extends AppCompatActivity {

    private List<Subject> subjects;
    private Integer position;

    private DatabaseReference dbRef;
    private EditText year;
    private EditText name;
    private EditText teacher;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);

        year = (EditText) findViewById(R.id.editGodina);
        name = (EditText) findViewById(R.id.editIme);
        teacher = (EditText) findViewById(R.id.editPredavac);
        btnSave = (Button) findViewById(R.id.btnSave);

        subjects = new ArrayList<>();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        dbRef = FirebaseDatabase.getInstance().getReference("vjezba6mobilne");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Subject data=ds.getValue(Subject.class);
                    subjects.add(data);
                }

                Subject target = subjects.get(position);
                year.setText(target.getYear().toString(), TextView.BufferType.EDITABLE);
                name.setText(target.getName(),TextView.BufferType.EDITABLE);
                teacher.setText(target.getTeacher(),TextView.BufferType.EDITABLE);

                subjects.remove(position);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setData() {
        Subject subject = new Subject();
        subject.setYear(Integer.parseInt(year.getText().toString()));
        subject.setName(name.getText().toString());
        subject.setTeacher(teacher.getText().toString());

        subjects.add(position, subject);
        dbRef.setValue(subjects);

        startActivity(new Intent(this, SubjectsActivity.class));
    }
}
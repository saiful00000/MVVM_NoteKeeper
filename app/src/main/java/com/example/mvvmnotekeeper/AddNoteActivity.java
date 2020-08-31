package com.example.mvvmnotekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.mvvmnotekeeper.models.Note;

public class AddNoteActivity extends AppCompatActivity {

    private NumberPicker numberPicker;
    private EditText titleEt, descriptionEt;

    public static final String TITLE = "com.example.mvvmnotekeeper.TITLE";
    public static final String DESCRIPTION = "com.example.mvvmnotekeeper.DESCRIPTION";
    public static final String PRIORITY = "com.example.mvvmnotekeeper.PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        getSupportActionBar().setTitle("Add Note");

        numberPicker = findViewById(R.id.numberpicker_id);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        titleEt = findViewById(R.id.title_et_id);
        descriptionEt = findViewById(R.id.description_et_id);

    }

    public void clearAllData(View view) {
        titleEt.setText("");
        descriptionEt.setText("");
    }

    public void addNote(View view) {
        String title = titleEt.getText().toString().trim();
        String description = descriptionEt.getText().toString().trim();
        int priority = numberPicker.getValue();

        if (title.isEmpty()) {
            titleEt.requestFocus();
            Toast.makeText(this, "Title is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (description.isEmpty()) {
            descriptionEt.requestFocus();
            Toast.makeText(this, "Description is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(TITLE, title);
        data.putExtra(DESCRIPTION, description);
        data.putExtra(PRIORITY, priority);
        setResult(RESULT_OK, data);
        finish();


    }
}
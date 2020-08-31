package com.example.mvvmnotekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.mvvmnotekeeper.models.Note;
import com.example.mvvmnotekeeper.view_models.NoteViewModel;

public class EditNoteActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    public static final String TITLE = "com.example.mvvmnotekeeper.TITLE";
    public static final String DESCRIPTION = "com.example.mvvmnotekeeper.DESCRIPTION";
    public static final String PRIORITY = "com.example.mvvmnotekeeper.PRIORITY";

    private NumberPicker numberPicker;
    private EditText titleEt;
    private EditText descriptionEt;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        numberPicker = findViewById(R.id.edit_numberpicker_id);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        titleEt = findViewById(R.id.edit_title_et_id);
        descriptionEt = findViewById(R.id.edit_description_et_id);

        getIntentDataAndShowOnFields();
    }

    private void getIntentDataAndShowOnFields() {
        id = getIntent().getIntExtra("note_id", 0);
        String title = getIntent().getStringExtra("note_title");
        String body = getIntent().getStringExtra("note_body");
        int priority = getIntent().getIntExtra("note_priority", 1);
        numberPicker.setValue(priority);
        titleEt.setText(title);
        descriptionEt.setText(body);
    }

    public void clearAllData(View view) {
        titleEt.setText("");
        descriptionEt.setText("");
    }

    public void updateNote(View view) {
        String title = titleEt.getText().toString().trim();
        String body = descriptionEt.getText().toString().trim();
        int priority = numberPicker.getValue();

        if (title.isEmpty()) {
            titleEt.requestFocus();
            Toast.makeText(this, "Title is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (body.isEmpty()) {
            descriptionEt.requestFocus();
            Toast.makeText(this, "Description is empty", Toast.LENGTH_SHORT).show();
            return;
        }


        Note note = new Note(title, body, priority);
        note.setId(id);
        noteViewModel.update(note);
        finish();
    }
}


















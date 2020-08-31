package com.example.mvvmnotekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmnotekeeper.databinding.ActivityViewNoteBinding;
import com.example.mvvmnotekeeper.models.Note;
import com.example.mvvmnotekeeper.models.ViewNote;

public class ViewNoteActivity extends AppCompatActivity {

    private ActivityViewNoteBinding activityViewNoteBinding;

    private int id;
    private String title;
    private String body;
    private int priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        activityViewNoteBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_note);

        getIntentData();


    }

    private void getIntentData() {
        id = getIntent().getIntExtra("note_id", 0);
        priority = getIntent().getIntExtra("note_priority", 0);
        title = getIntent().getStringExtra("note_title");
        body = getIntent().getStringExtra("note_body");

        activityViewNoteBinding.setViewnote(new ViewNote(id, title, body, Integer.toString(priority)));

    }

    public void editFabClick(View view) {
        Intent intent = new Intent(ViewNoteActivity.this, EditNoteActivity.class);
        intent.putExtra("note_id", id);
        intent.putExtra("note_title", title);
        intent.putExtra("note_body", body);
        intent.putExtra("note_priority", priority);
        startActivity(intent);
        finish();
    }
}
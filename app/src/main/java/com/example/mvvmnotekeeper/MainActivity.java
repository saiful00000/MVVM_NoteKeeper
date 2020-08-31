package com.example.mvvmnotekeeper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mvvmnotekeeper.adapters.NoteListAdapter;
import com.example.mvvmnotekeeper.interfaces.ItemClick;
import com.example.mvvmnotekeeper.models.Note;
import com.example.mvvmnotekeeper.view_models.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClick {

    private NoteViewModel noteViewModel;


    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private NoteListAdapter adapter;

    public static final int NOTE_INSERT_REQUEST_CODE = 1;
    public static final int NOTE_UPDATE_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fab = findViewById(R.id.fab_id);
        recyclerView = findViewById(R.id.recycler_view_id);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                if (notes != null) {
                    adapter = new NoteListAdapter(MainActivity.this, notes, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    for (Note note : notes)
                        Log.i("main", note.getTitle());
                } else
                    Log.i("main", "notes list is null");

            }
        });


        recyclerViewItemSwipeHandler();

    }

    private void recyclerViewItemSwipeHandler() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Note note = adapter.getNoteAt(position);
                noteViewModel.delete(note);
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void fabClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivityForResult(intent, NOTE_INSERT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NOTE_INSERT_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.TITLE);
            String description = data.getStringExtra(AddNoteActivity.DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.PRIORITY, 1);

            Note note = new Note(title, description, priority);
            noteViewModel.insert(note);
        }/*else if (requestCode == NOTE_UPDATE_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.TITLE);
            String description = data.getStringExtra(AddNoteActivity.DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.PRIORITY, 1);

            Note note = new Note(title, description, priority);
            noteViewModel.update(note);
        }*/
    }

    @Override
    public void onItemClick(Note note) {
        Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
        intent.putExtra("note_id", note.getId());
        intent.putExtra("note_title", note.getTitle());
        intent.putExtra("note_body", note.getBody());
        intent.putExtra("note_priority", note.getPriority());
        startActivity(intent);
    }
}
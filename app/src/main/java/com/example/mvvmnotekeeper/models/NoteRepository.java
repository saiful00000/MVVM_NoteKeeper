package com.example.mvvmnotekeeper.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allnotes;


    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allnotes = noteDao.getAllNote();
    }


    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }


    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }


    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }


    public void deletAllNote() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }


    public LiveData<List<Note>> getAllnotes() {
        return allnotes;
    }


    // inser async task
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        public InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    // update async task
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        public UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }


    // delete a note async task
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        public DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    // delete all async task
    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        public DeleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}

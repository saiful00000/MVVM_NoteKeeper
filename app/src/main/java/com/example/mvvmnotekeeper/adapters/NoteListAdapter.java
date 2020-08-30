package com.example.mvvmnotekeeper.adapters;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmnotekeeper.R;
import com.example.mvvmnotekeeper.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private Context context;
    private List<Note> notes = new ArrayList<>();


    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_custom_item, parent, false);

        return new NoteListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListViewHolder holder, final int position) {

        Note note = notes.get(position);

        holder.titleTv.setText(note.getTitle());
        holder.bodyTv.setText(note.getBody());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
                Log.i("adapter", Integer.toString(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView bodyTv;
        ImageView deleteBtn;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.title_textview_id);
            bodyTv = itemView.findViewById(R.id.body_textview_id);
            deleteBtn = itemView.findViewById(R.id.delete_btn_id);

        }
    }


    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

package com.example.mvvmnotekeeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmnotekeeper.R;
import com.example.mvvmnotekeeper.interfaces.ItemClick;
import com.example.mvvmnotekeeper.models.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private ItemClick itemClickCallback;
    private Context context;
    private List<Note> notes;


    public NoteListAdapter(Context context, List<Note> notes,ItemClick itemClickCallback) {
        this.context = context;
        this.notes = notes;
        this.itemClickCallback = itemClickCallback;
    }

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
        int priority = note.getPriority();

        if (priority < 2){
            holder.deleteBtn.setImageResource(R.drawable.ic_baseline_priority_green_high_24);
        } else if (priority == 3) {
            holder.deleteBtn.setImageResource(R.drawable.ic_baseline_priority_yelow_high_24);
        }
        holder.titleTv.setText(note.getTitle());
        holder.bodyTv.setText(note.getBody());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv;
        TextView bodyTv;
        ImageView deleteBtn;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.title_textview_id);
            bodyTv = itemView.findViewById(R.id.body_textview_id);
            deleteBtn = itemView.findViewById(R.id.delete_btn_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    itemClickCallback.onItemClick(notes.get(position));
                }
            });

        }
    }


    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int i) {
        return notes.get(i);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

package com.kkandroid.notesdigital.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kkandroid.notesdigital.R;
import com.kkandroid.notesdigital.entities.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> noteList;

    public NotesAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
    holder.setNote(noteList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTextTitle, tvTextSubtitle, tvTextDateTime;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTextTitle = itemView.findViewById(R.id.textTitle);
            tvTextSubtitle = itemView.findViewById(R.id.textSubtitle);
            tvTextDateTime = itemView.findViewById(R.id.textDateTime);

        }

        void setNote(Note note) {
            tvTextTitle.setText(note.getTitle());
            if (note.getSubtitle().trim().isEmpty()) {
                tvTextSubtitle.setVisibility(View.GONE);
            } else {
                tvTextSubtitle.setText(note.getSubtitle());
            }
            tvTextDateTime.setText(note.getDatetime());
        }
    }
}

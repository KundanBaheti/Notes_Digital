package com.kkandroid.notesdigital.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kkandroid.notesdigital.R;
import com.kkandroid.notesdigital.entities.Note;
import com.kkandroid.notesdigital.listener.NotesListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private NotesListener notesListener;

//    public NotesAdapter(List<Note> noteList) {
//        this.noteList = noteList;
//    }

    public NotesAdapter(List<Note> noteList, NotesListener notesListener) {
        this.noteList = noteList;
        this.notesListener = notesListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(noteList.get(position));
        holder.llAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesListener.onNoteClicked(noteList.get(position), position);
            }
        });
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
        private LinearLayout llAdapter;
        private RoundedImageView rivImageNote;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTextTitle = itemView.findViewById(R.id.textTitle);
            tvTextSubtitle = itemView.findViewById(R.id.textSubtitle);
            tvTextDateTime = itemView.findViewById(R.id.textDateTime);
            llAdapter = itemView.findViewById(R.id.layout_note);
            rivImageNote = itemView.findViewById(R.id.imageNote);


        }

        void setNote(Note note) {
            tvTextTitle.setText(note.getTitle());
            if (note.getSubtitle().trim().isEmpty()) {
                tvTextSubtitle.setVisibility(View.GONE);
            } else {
                tvTextSubtitle.setText(note.getSubtitle());
            }
            tvTextDateTime.setText(note.getDatetime());

            GradientDrawable gradientDrawable = (GradientDrawable) llAdapter.getBackground();
            if (note.getColor() != null) {
                gradientDrawable.setColor(Color.parseColor(note.getColor()));
            } else {
                gradientDrawable.setColor(Color.parseColor("#333333"));
            }

            if (note.getImage() != null) {
                rivImageNote.setImageBitmap(BitmapFactory.decodeFile(note.getImage()));
                rivImageNote.setVisibility(View.VISIBLE);
            } else {
                rivImageNote.setVisibility(View.GONE);
            }
        }

    }
}

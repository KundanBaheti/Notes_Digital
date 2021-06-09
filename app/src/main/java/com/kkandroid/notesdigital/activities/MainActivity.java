package com.kkandroid.notesdigital.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.kkandroid.notesdigital.R;
import com.kkandroid.notesdigital.adapters.NotesAdapter;
import com.kkandroid.notesdigital.database.NotesDatabase;
import com.kkandroid.notesdigital.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    ImageView ivAddNote;
    private RecyclerView rvNotes;
    private List<Note> noteList;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivAddNote = findViewById(R.id.add_note);
        ivAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, CreateNoteActivity.class),
                        REQUEST_CODE_ADD_NOTE);
            }
        });
        rvNotes = findViewById(R.id.notesRecyclerView);
        rvNotes.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList);
        rvNotes.setAdapter(notesAdapter);
        getNotes();
    }

    private void getNotes() {
        class GetAllNoteTask extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getNotesDatabase(getApplicationContext()).noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                if (noteList.size() == 0) {
                    noteList.addAll(notes);
                    notesAdapter.notifyDataSetChanged();
                } else {
                    noteList.add(0, notes.get(0));
                    notesAdapter.notifyDataSetChanged();
                }
                rvNotes.smoothScrollToPosition(0);
                Log.d("onPostExecute: ", notes.toString());
            }
        }
        new GetAllNoteTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_ADD_NOTE&&resultCode==RESULT_OK){
            getNotes();
        }
    }
}
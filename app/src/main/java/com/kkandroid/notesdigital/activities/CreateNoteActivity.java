package com.kkandroid.notesdigital.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kkandroid.notesdigital.R;
import com.kkandroid.notesdigital.database.NotesDatabase;
import com.kkandroid.notesdigital.entities.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {
    ImageView iv_back, ivSave;
    private EditText edtInputNoteTitle, edtInputNoteSubtitle, edtInputNoteText;
    private TextView tvDateTime;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        iv_back = findViewById(R.id.imageBack);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edtInputNoteTitle = findViewById(R.id.inputNoteTitle);
        edtInputNoteSubtitle = findViewById(R.id.inputNoteSubtitle);
        edtInputNoteText = findViewById(R.id.inputNote);
        tvDateTime = findViewById(R.id.textDataTime);
        tvDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        ivSave = findViewById(R.id.imageSave);
        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });


    }

    private void saveNote() {
        if (edtInputNoteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note title can't be empty", Toast.LENGTH_SHORT).show();
            return;
        } else if ((edtInputNoteText.getText().toString().trim().isEmpty()) && (edtInputNoteSubtitle.getText().toString().trim().isEmpty())) {
            Toast.makeText(this, "Note can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        final Note note = new Note();
        note.setTitle(edtInputNoteTitle.getText().toString());
        note.setSubtitle(edtInputNoteSubtitle.getText().toString());
        note.setNoteText(edtInputNoteText.getText().toString());
        note.setDatetime(tvDateTime.getText().toString());

        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getNotesDatabase(getApplicationContext()).noteDao().insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
//                super.onPostExecute(aVoid);
            }

        }
        new SaveNoteTask().execute();
    }

}
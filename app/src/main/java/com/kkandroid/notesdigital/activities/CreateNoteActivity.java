package com.kkandroid.notesdigital.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
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
    private String selectedNoteColor;
    private View viewSubtitleColor;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        viewSubtitleColor = findViewById(R.id.viewSubtitleIndicator);
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
        selectedNoteColor = "#333333";
        selectedNoteColor = "#333333";

        initMiscellaneous();
        setViewSubtitleIndicatorColor();

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
        note.setColor(selectedNoteColor);

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

    private void initMiscellaneous() {
        final LinearLayout layoutMisellaneous = findViewById(R.id.layout_miscellaneous);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(layoutMisellaneous);
        layoutMisellaneous.findViewById(R.id.textMiscellaneous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        final ImageView imageColor1 = layoutMisellaneous.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = layoutMisellaneous.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = layoutMisellaneous.findViewById(R.id.imageColor3);
        final ImageView imageColor4 = layoutMisellaneous.findViewById(R.id.imageColor4);
        final ImageView imageColor5 = layoutMisellaneous.findViewById(R.id.imageColor5);

      View view1 =findViewById(R.id.viewColor1);
      view1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              selectedNoteColor = "#333333";
              imageColor1.setImageResource(R.drawable.ic_done);
              imageColor2.setImageResource(0);
              imageColor3.setImageResource(0);
              imageColor4.setImageResource(0);
              imageColor5.setImageResource(0);
              setViewSubtitleIndicatorColor();
          }
      });
        View view2 =findViewById(R.id.viewColor2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#FDBE3B";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(R.drawable.ic_done);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setViewSubtitleIndicatorColor();
            }
        });
        View view3 =findViewById(R.id.viewColor3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#FF4842";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(R.drawable.ic_done);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setViewSubtitleIndicatorColor();
            }
        });
        View view4 =findViewById(R.id.viewColor4);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#3A52FC";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(R.drawable.ic_done);
                imageColor5.setImageResource(0);
                setViewSubtitleIndicatorColor();
            }
        });
        View view5 =findViewById(R.id.viewColor5);
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNoteColor = "#000000";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(R.drawable.ic_done);
                setViewSubtitleIndicatorColor();
            }
        });
//      layoutMisellaneous.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#333333";
//                imageColor1.setImageResource(R.drawable.ic_done);
//                imageColor2.setImageResource(0);
//                imageColor3.setImageResource(0);
//                imageColor4.setImageResource(0);
//                imageColor5.setImageResource(0);
//                setViewSubtitleIndicatorColor();
//            }
//        });
//        layoutMisellaneous.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#FDBE3B";
//                imageColor1.setImageResource(0);
//                imageColor2.setImageResource(R.drawable.ic_done);
//                imageColor3.setImageResource(0);
//                imageColor4.setImageResource(0);
//                imageColor5.setImageResource(0);
//                setViewSubtitleIndicatorColor();
//            }
//        });
//        layoutMisellaneous.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#FF4842";
//                imageColor1.setImageResource(0);
//                imageColor2.setImageResource(0);
//                imageColor3.setImageResource(R.drawable.ic_done);
//                imageColor4.setImageResource(0);
//                imageColor5.setImageResource(0);
//                setViewSubtitleIndicatorColor();
//            }
//        });
//        layoutMisellaneous.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#3A52FC";
//                imageColor1.setImageResource(0);
//                imageColor2.setImageResource(0);
//                imageColor3.setImageResource(0);
//                imageColor4.setImageResource(R.drawable.ic_done);
//                imageColor5.setImageResource(0);
//                setViewSubtitleIndicatorColor();
//            }
//        });
//        layoutMisellaneous.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#000000";
//                imageColor1.setImageResource(0);
//                imageColor2.setImageResource(0);
//                imageColor3.setImageResource(0);
//                imageColor4.setImageResource(0);
//                imageColor5.setImageResource(R.drawable.ic_done);
//                setViewSubtitleIndicatorColor();
//            }
//        });

    }

    private void setViewSubtitleIndicatorColor() {
        GradientDrawable gradientDrawable = (GradientDrawable) viewSubtitleColor.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedNoteColor));
    }
}
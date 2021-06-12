package com.kkandroid.notesdigital.listener;

import com.kkandroid.notesdigital.entities.Note;

public interface NotesListener {

    void onNoteClicked(Note note, int position);

}

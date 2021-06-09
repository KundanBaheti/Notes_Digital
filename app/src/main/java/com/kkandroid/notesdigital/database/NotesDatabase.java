package com.kkandroid.notesdigital.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.kkandroid.notesdigital.dao.NoteDao;
import com.kkandroid.notesdigital.entities.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase notesDatabase;
    public abstract NoteDao noteDao();
    public static synchronized NotesDatabase getNotesDatabase(Context context) {
        if (notesDatabase == null) {
            notesDatabase = Room.databaseBuilder(context, NotesDatabase.class, "notes_db").build();
        }
        return notesDatabase;
    }

}

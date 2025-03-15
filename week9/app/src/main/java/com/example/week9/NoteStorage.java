package com.example.week9;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.ArrayList;
public class NoteStorage {
    private ArrayList<Note> notes = new ArrayList<>();

    private static NoteStorage storage = null;


    private NoteStorage() {

    }

    public static NoteStorage getInstance() {
        if(storage == null) {
            storage = new NoteStorage();
        }
        return storage;
    }

    public ArrayList<Note> getNotes () {
        return notes;
    }

    public void addNote(Note note) {
       notes.add(note);
    }
}

package com.mb.notesapp.repository

import androidx.annotation.NonNull
import com.mb.notesapp.db.db
import com.mb.notesapp.model.NoteModel

class NoteRepository {

    suspend fun addNote(note: NoteModel) = db.noteDao().insert(note)

    suspend fun updateNote(note : NoteModel) = db.noteDao().update(note)

    suspend fun deleteNote(note : NoteModel) = db.noteDao().delete(note)

    suspend fun deleteAllNote() = db.noteDao().deleteAll()

    fun getAll() = db.noteDao().getAllNotes()
}
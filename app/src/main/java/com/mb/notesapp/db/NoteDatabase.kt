package com.mb.notesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mb.notesapp.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
        abstract fun noteDao() : NoteDao
}
package com.mb.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mb.notesapp.model.NoteModel



@Volatile
private var instance : NoteDatabase? = null

val db : NoteDatabase get() = instance!!

fun getDatabase(context: Context){
    if (instance == null){
        instance = Room.databaseBuilder(
            context,
            NoteDatabase::class.java,"note-db"
        ).fallbackToDestructiveMigration().build()
            }
}


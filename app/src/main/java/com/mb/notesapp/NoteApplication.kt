package com.mb.notesapp

import android.app.Application
import com.mb.notesapp.db.getDatabase

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        getDatabase(this)
    }
}
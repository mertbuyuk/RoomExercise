package com.mb.notesapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.mb.notesapp.model.NoteModel

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: NoteModel)

    @Update
    suspend fun update(note : NoteModel)

    @Delete
    suspend fun delete(note : NoteModel)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY _priority ASC")
    fun getAllNotes() : LiveData<List<NoteModel>>

}
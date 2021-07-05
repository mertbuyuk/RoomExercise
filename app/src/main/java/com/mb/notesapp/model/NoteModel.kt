package com.mb.notesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "note_table")

data class NoteModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    private var _title: String,
    private var _description: String,
    private var _priority: Int
){

    val title get() = _title
    val description get() = _description
    val priority get() = _priority

}

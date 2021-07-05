package com.mb.notesapp.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mb.notesapp.model.NoteModel
import com.mb.notesapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository : NoteRepository)  : ViewModel() {

    private val _allNotes = MutableLiveData<List<NoteModel>>()
    val allNotes : LiveData<List<NoteModel>> get() = _allNotes

    fun addNotes(note: NoteModel) = viewModelScope.launch {
        noteRepository.addNote(note)
    }

    fun updateNote(note : NoteModel) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note : NoteModel) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun deleteAll() = viewModelScope.launch {
        noteRepository.deleteAllNote()
    }

    fun orderAndGetAll() = noteRepository.getAll()






}
package com.tunahan.notepadkotlin.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDao
import com.tunahan.notepadkotlin.room.NoteDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class NoteRepository(private val noteDao: NoteDao) {


    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    fun searchNote(query:String?)=noteDao.searchNote(query)

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }


}
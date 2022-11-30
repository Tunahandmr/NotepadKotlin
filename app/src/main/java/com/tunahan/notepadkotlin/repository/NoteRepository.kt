package com.tunahan.notepadkotlin.repository

import androidx.lifecycle.LiveData
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    fun searchNote(query: String?) = noteDao.searchNote(query)
    fun spinnerNote(spin: Int?) = noteDao.spinnerNote(spin)

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
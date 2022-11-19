package com.tunahan.notepadkotlin.util

import androidx.lifecycle.LiveData
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

}
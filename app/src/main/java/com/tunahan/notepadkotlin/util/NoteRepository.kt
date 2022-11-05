package com.tunahan.notepadkotlin.util

import androidx.lifecycle.LiveData
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note.id,note.title,note.text)
    }

}
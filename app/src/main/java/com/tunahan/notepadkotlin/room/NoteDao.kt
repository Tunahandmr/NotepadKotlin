package com.tunahan.notepadkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tunahan.notepadkotlin.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>
}
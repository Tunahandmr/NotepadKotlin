package com.tunahan.notepadkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tunahan.notepadkotlin.model.Note
import java.util.concurrent.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table WHERE title LIKE :query OR text LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>





}
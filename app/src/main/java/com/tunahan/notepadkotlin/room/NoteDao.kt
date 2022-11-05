package com.tunahan.notepadkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tunahan.notepadkotlin.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("UPDATE notes_table Set title = :title,text=:text WHERE id = :id")
    suspend fun update(id: Int?, title: String?, text: String?)
}
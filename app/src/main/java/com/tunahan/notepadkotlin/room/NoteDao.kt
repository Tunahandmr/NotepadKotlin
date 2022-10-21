package com.tunahan.notepadkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tunahan.notepadkotlin.model.Note

@Dao
interface NoteDao {


    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id IN (:noteIds)")
    suspend fun loadAllByIds(noteIds: IntArray): List<Note>

    @Insert
    suspend fun insertAll(vararg note: Note)

    @Update
    suspend fun Update(note: Note)

    @Delete
    suspend fun delete(note: Note)


/*
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
 */
}
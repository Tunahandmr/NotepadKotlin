package com.tunahan.notepadkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDatabase
import com.tunahan.notepadkotlin.util.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository

    val allNotes: LiveData<List<Note>>

    init {

        val dao = NoteDatabase.getDatabase(application).NoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note)=viewModelScope.launch  (Dispatchers.IO){
        repository.delete(note)
    }

    fun inserteNote(note: Note)=viewModelScope.launch  (Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(note: Note)=viewModelScope.launch  (Dispatchers.IO){
        repository.update(note)
    }
/*
     val noteLiveData = MutableLiveData<Note>()

     fun getDataFromRoom(uuid:Int){

            val dao = NoteDatabase(getApplication()).NoteDao()
            val note = dao.getAllNotes(uuid)
            noteLiveData.value = note


    }

 */

}
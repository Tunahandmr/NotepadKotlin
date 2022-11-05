package com.tunahan.notepadkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(application: Application) : AndroidViewModel(application) {


    val notes = MutableLiveData<List<Note>>()

    private fun getDataFromSQLite(uuid: Int) {
       // val notes = NoteDatabase(getApplication()).NoteDao().getAll(uuid)
        //showCountries(notes)
    }

    private fun showCountries(noteList: List<Note>) {
        notes.value = noteList
    }


}
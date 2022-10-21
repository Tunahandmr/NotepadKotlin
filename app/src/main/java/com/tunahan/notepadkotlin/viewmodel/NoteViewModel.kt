package com.tunahan.notepadkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDatabase
import com.tunahan.notepadkotlin.util.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application) {



}
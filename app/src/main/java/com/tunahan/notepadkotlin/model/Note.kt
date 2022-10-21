package com.tunahan.notepadkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "time")
    val time:String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


}

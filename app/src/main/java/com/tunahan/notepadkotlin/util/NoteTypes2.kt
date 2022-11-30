package com.tunahan.notepadkotlin.util

import com.tunahan.notepadkotlin.R

data class NoteTypes2(val image: Int, val name: String) {
    object Notes {

        private val images = intArrayOf(
            R.color.transparan,
            R.drawable.gray_circle,
            R.drawable.blue_circle,
            R.drawable.yellow_circle,
            R.drawable.red_circle
        )

        private val notes = arrayOf(
            "All Notes",
            "No Category",
            "Personal",
            "Travel",
            "Work"
        )

        var list: ArrayList<NoteTypes>? = null
            get() {
                if (field != null)
                    return field
                field = ArrayList()
                for (i in images.indices) {
                    val noteId = images[i]
                    val notes = notes[i]


                    val noteTypes = NoteTypes(noteId, notes)
                    field!!.add(noteTypes)

                }
                return field
            }
    }
}

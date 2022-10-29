package com.tunahan.notepadkotlin.util

import com.tunahan.notepadkotlin.R

data class NoteTypes(val image:Int,val name:String) {

    object Notes {

        private val images = intArrayOf(
            R.drawable.gray_circle,
            R.drawable.blue_circle,
            R.drawable.yellow_circle,
            R.drawable.red_circle
        )

        private val notes = arrayOf(
            "no category",
            "Personal",
            "Travel",
            "Work"
        )

        var list:ArrayList<NoteTypes>?=null
        get() {
            if (field!=null)
                return field
            field = ArrayList()
            for (i in images.indices){
                val imageId=images[i]
                val countryName = notes[i]


                val noteTypes = NoteTypes(imageId,countryName)
                field!!.add(noteTypes)

            }
            return field
        }
    }


}
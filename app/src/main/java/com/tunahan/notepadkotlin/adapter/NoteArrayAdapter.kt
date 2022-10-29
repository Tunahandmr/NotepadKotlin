package com.tunahan.notepadkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.util.NoteTypes
import kotlinx.android.synthetic.main.spinner_item.view.*

class NoteArrayAdapter(context: Context, noteList: List<NoteTypes>):ArrayAdapter<NoteTypes>(context,0,noteList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int,convertView: View?,parent: ViewGroup):View{

        val note = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_item,parent,false)
        view.imageView.setImageResource(note!!.image)
        view.textView.text =note.name

        return view
    }
}
package com.tunahan.notepadkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.databinding.RecyclerRowBinding
import com.tunahan.notepadkotlin.model.Note
import kotlinx.android.synthetic.main.fragment_main.view.*

class NotepadAdapter(private val noteList:ArrayList<Note>):RecyclerView.Adapter<NotepadAdapter.NoteViewHolder>() {

    class NoteViewHolder(val view: RecyclerRowBinding):RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val v = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(v)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.view.recyclerViewTitleText.text = noteList[position].title
        holder.view.recyclerViewNoteText.text = noteList[position].text
        holder.view.recyclerViewDateText.text = noteList[position].time

    }

    override fun getItemCount(): Int {
        return noteList.size
    }


}
package com.tunahan.notepadkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.car.ui.toolbar.MenuItem.Listener
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.databinding.RecyclerRowBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.view.MainFragmentDirections
import kotlinx.android.synthetic.main.fragment_main.view.*

class NotepadAdapter(private val noteList: ArrayList<Note>) :
    RecyclerView.Adapter<NotepadAdapter.NoteViewHolder>(){


    class NoteViewHolder(val view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val v = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(v)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.view.recyclerViewTitleText.text = noteList[position].title
        holder.view.recyclerViewTitleText.isSelected = true
        holder.view.recyclerViewNoteText.text = noteList[position].text
        holder.view.recyclerViewDateText.text = noteList[position].time
        holder.view.recyclerViewDateText.isSelected = true

        holder.itemView.setOnClickListener {
            val action = noteList[position].id?.let { it1 ->
                MainFragmentDirections.actionMainFragmentToNoteFragment(
                    it1, "old")
            }
            action?.let { it1 -> Navigation.findNavController(it).navigate(it1) }

        }
    }



    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateNotes(newNoteList:List<Note>){
        noteList.clear()
        noteList.addAll(newNoteList)
        notifyDataSetChanged()
    }




}
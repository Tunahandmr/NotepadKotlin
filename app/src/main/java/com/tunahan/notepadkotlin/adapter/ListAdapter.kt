package com.tunahan.notepadkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.model.Note
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var notelist = emptyList<Note>()
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notelist[position]
        holder.itemView.title_id.text = currentItem.title
        holder.itemView.note_id.text = currentItem.text
    }

    override fun getItemCount(): Int {
        return notelist.size
    }

    fun setData(note: List<Note>){
        this.notelist = note
        notifyDataSetChanged()
    }
}
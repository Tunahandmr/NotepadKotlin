package com.tunahan.notepadkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.view.MainFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlin.collections.ArrayList

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // private var noteEmptyList = emptyList<Note>()
    var noteList = ArrayList<Note>()

    private val colors: Array<String> =
        arrayOf("#2a22a2", "#f5f5f5", "#b0fffe", "#ffeeb8", "#ffb0b0")

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(noteModel: Note, colors: Array<String>, num: Int?) {

            itemView.row_layout.setCardBackgroundColor(Color.parseColor(colors[num!!]))
            itemView.title_id.text = noteModel.title
            itemView.note_id.text = noteModel.text
            itemView.date_id.text = noteModel.time

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.bind(currentItem, colors, currentItem.type)

        holder.itemView.row_layout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(currentItem)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun setData(note: List<Note>) {

        noteList = note as ArrayList<Note>
        notifyDataSetChanged()

        /*this.countryFilterList = note as ArrayList<Note>
        notifyDataSetChanged()*/
    }

}


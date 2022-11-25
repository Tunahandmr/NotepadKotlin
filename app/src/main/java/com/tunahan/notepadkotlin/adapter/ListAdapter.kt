package com.tunahan.notepadkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.view.MainFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


   // private var noteEmptyList = emptyList<Note>()

    var countryFilterList = ArrayList<Note>()
  //  var countryList = ArrayList<Note>()
    lateinit var mContext: Context

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return countryFilterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = countryFilterList[position]
        holder.itemView.title_id.text = currentItem.title
        holder.itemView.note_id.text = currentItem.text


        holder.itemView.row_layout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(currentItem)
            Navigation.findNavController(it).navigate(action)
        }


    }


    fun setData(note: List<Note>){

        countryFilterList = note as ArrayList<Note>
        notifyDataSetChanged()
/*
        this.countryFilterList = note as ArrayList<Note>
        notifyDataSetChanged()*/
    }


}
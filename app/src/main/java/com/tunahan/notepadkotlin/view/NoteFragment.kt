package com.tunahan.notepadkotlin.view

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.databinding.FragmentNoteBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel


abstract class NoteFragment : Fragment()/*,MenuProvider*/ {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NoteViewModel
    private var myUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        //val menuHost: MenuHost = requireActivity()
        // menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            save(it)
        }

        arguments?.let {
            myUuid = NoteFragmentArgs.fromBundle(it).uuid
        }

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        //viewModel.allNotes(myUuid)

        //observeLiveData()

        setupCustomSpinner()
    }

    private fun setupCustomSpinner() {
        val adapter = NoteArrayAdapter(requireContext(), NoteTypes.Notes.list!!)
        binding.spinner.adapter = adapter
    }

    private fun save(view: View){
        val title = binding.titleEditText.text.toString()
        val text = binding.bodyEditText.text.toString()

        val note = Note(title,text,"pazartesi",1)

    }
/*
    private fun observeLiveData(){

        viewModel.noteLiveData.observe(viewLifecycleOwner,Observer{note->
            note?.let {

                binding.titleEditText.text = note.title
                binding.bodyEditText.text = note.text
                binding.spinner.
            }



        })
    }

 */
/*
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.my_menu,menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.save_menu ->{Toast.makeText(requireContext(),"saved",Toast.LENGTH_SHORT).show()}
            R.id.delete_menu ->{Toast.makeText(requireContext(),"delete",Toast.LENGTH_SHORT).show()}

        }
        return true
        }

 */


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
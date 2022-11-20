package com.tunahan.notepadkotlin.view


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.databinding.FragmentNoteBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        binding.saveButton.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase(){
        val title = binding.titleEditText.text.toString()
        val note = binding.bodyEditText.text.toString()

        if (inputCheck(title,note)){
            //create note object
            val notes = Note(0,title,note,"august",0)
            //add data to database
            mNoteViewModel.addNote(notes)
            Toast.makeText(requireContext(),"added succesfully",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_noteFragment_to_mainFragment)
        }else{
            Toast.makeText(requireContext(),"added not succesfully!!!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title:String,note:String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_mainFragment)
        }
        setupCustomSpinner()
    }

    private fun setupCustomSpinner() {
        val adapter = NoteArrayAdapter(requireContext(), NoteTypes.Notes.list!!)
        binding.spinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
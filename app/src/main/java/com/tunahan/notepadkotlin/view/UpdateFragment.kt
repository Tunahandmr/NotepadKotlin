package com.tunahan.notepadkotlin.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.car.ui.AlertDialogBuilder
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.databinding.FragmentUpdateBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {


    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val view = binding.root

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        view.updateTitleEditText.setText(args.currentNote.title)
        view.updateBodyEditText.setText(args.currentNote.text)

        binding.saveButtonUpdate.setOnClickListener {
            updateNote()
        }
        return view
    }

    private fun updateNote() {
        val title = updateTitleEditText.text.toString()
        val note = updateBodyEditText.text.toString()

        if (inputCheck(title, note)) {

            val updatedNote = Note(args.currentNote.id, title, note, "august", 0)
            mNoteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(), "updated succesfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "updated not succesfully!!!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(title: String, note: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteButtonUpdate.setOnClickListener {
            deleteNote()
        }
        setupCustomSpinner()
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(),"Succesfully removed: ${args.currentNote.title}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }

    private fun setupCustomSpinner() {
        val adapter = NoteArrayAdapter(requireContext(), NoteTypes.Notes.list!!)
        binding.updateSpinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}
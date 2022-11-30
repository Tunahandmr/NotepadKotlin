package com.tunahan.notepadkotlin.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.SpinnerAdapter
import com.tunahan.notepadkotlin.databinding.FragmentUpdateBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.text.SimpleDateFormat
import java.util.*


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    var currentDate: String = ""
    var spinNum: Int = 0
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        //get note arguments
        binding.updateTitleEditText.setText(args.currentNote.title)
        binding.updateBodyEditText.setText(args.currentNote.text)

        //date
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm aaa")
        currentDate = sdf.format(Date())
        binding.dateText.text = currentDate

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //spinner initial value
        binding.updateSpinner.setSelection(args.currentNote.type!! - 1, true)
    }

    private fun updateNote() {
        val title = updateTitleEditText.text.toString()
        val note = updateBodyEditText.text.toString()

        if (inputCheck(title, note)) {

            val updatedNote = Note(args.currentNote.id, title, note, currentDate, spinNum)
            mNoteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(), "Update Successful", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Update Not Successful", Toast.LENGTH_SHORT).show()
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

        binding.backButtonUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }

        binding.saveButtonUpdate.setOnClickListener {
            updateNote()
        }

        setupCustomSpinner()

        //spinner create
        binding.updateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinNum = p2 + 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun deleteNote() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(
                requireContext(),
                "Succesfully removed: ${args.currentNote.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }

    private fun setupCustomSpinner() {
        val adapter = SpinnerAdapter(requireContext(), NoteTypes.Notes.list!!)
        binding.updateSpinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}
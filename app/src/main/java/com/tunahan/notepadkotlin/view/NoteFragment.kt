package com.tunahan.notepadkotlin.view


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.SpinnerAdapter
import com.tunahan.notepadkotlin.databinding.FragmentNoteBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    var currentDate: String = ""
    var spinNum: Int = 0
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        //date
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm aaa")
        currentDate = sdf.format(Date())
        binding.dateText.text = currentDate

        return binding.root
    }

    private fun insertDataToDatabase() {
        val title = binding.titleEditText.text.toString()
        val note = binding.bodyEditText.text.toString()

        if (inputCheck(title, note)) {
            //create note object
            val notes = Note(0, title, note, currentDate, spinNum)
            //add data to database
            mNoteViewModel.addNote(notes)
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_noteFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Successfully Not Added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, note: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_mainFragment)
        }

        binding.saveButton.setOnClickListener {
            insertDataToDatabase()
        }

        setupCustomSpinner()

        //spinner create
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinNum = p2 + 1

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun setupCustomSpinner() {
        val adapter = SpinnerAdapter(requireContext(), NoteTypes.Notes.list!!)
        binding.spinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.tunahan.notepadkotlin.view

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.provider.CalendarContract.Instances
import android.view.*
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.adapter.NotepadAdapter
import com.tunahan.notepadkotlin.databinding.FragmentMainBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDatabase
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: NoteDatabase
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NotepadAdapter
    lateinit var selectedNote: Note

    private val updateNote = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if (result.resultCode==Activity.RESULT_OK){
            val note=result.data?.getSerializableExtra("note") as? Note

            if (note!=null){

                viewModel.updateNote(note)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCustomSpinner()


        binding.floatingActionButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }


    private fun setupCustomSpinner() {
        val adapter = NoteArrayAdapter(requireContext(), NoteTypes.Notes.list!!)
        binding.spinner2.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
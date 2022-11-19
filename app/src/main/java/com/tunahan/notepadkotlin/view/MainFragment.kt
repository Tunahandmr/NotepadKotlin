package com.tunahan.notepadkotlin.view

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.ListAdapter
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.databinding.FragmentMainBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.room.NoteDao
import com.tunahan.notepadkotlin.room.NoteDatabase
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNoteViewModel: NoteViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        //recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //viewmodel
        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note->
            adapter.setData(note)

        })
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCustomSpinner()

        binding.floatingActionButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNoteFragment()
            Navigation.findNavController(it).navigate(action)
           // findNavController().navigate(R.id.action_mainFragment_to_noteFragment)
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
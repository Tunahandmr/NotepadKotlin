package com.tunahan.notepadkotlin.view

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahan.notepadkotlin.adapter.ListAdapter
import com.tunahan.notepadkotlin.adapter.SpinnerAdapter
import com.tunahan.notepadkotlin.databinding.FragmentMainBinding
import com.tunahan.notepadkotlin.util.NoteTypes2
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    var spinNum = 0
    private lateinit var mNoteViewModel: NoteViewModel
    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        //recyclerview
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //viewmodel
        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCustomSpinner()

        searchView()
        spinnerFilter()

        binding.floatingActionButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNoteFragment()
            Navigation.findNavController(it).navigate(action)

        }
    }

    private fun spinnerFilter() {

        //spinner create
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                spinNum = p2
                if (p2 == 0) {
                    mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
                        adapter.setData(note)
                    })

                } else {

                    mNoteViewModel.spinnerNote(p2).observe(viewLifecycleOwner) { list ->
                        adapter.setData(list)

                    }

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(requireContext(), "tost", Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun searchView() {

        //searchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchNotes(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchNotes(newText)
                } else {
                    spinnerFilter()
                }

                return true
            }

        })

    }

    private fun searchNotes(query: String?) {
        val searchQuery = "%$query%"
        mNoteViewModel.searchNote(searchQuery).observe(this) { list ->
            adapter.setData(list)
        }
    }

    private fun setupCustomSpinner() {
        val adapter = SpinnerAdapter(requireContext(), NoteTypes2.Notes.list!!)
        binding.spinner2.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}



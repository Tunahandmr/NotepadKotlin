package com.tunahan.notepadkotlin.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.adapter.NotepadAdapter
import com.tunahan.notepadkotlin.databinding.FragmentMainBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.util.NoteTypes


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val notepadAdapter = NotepadAdapter(arrayListOf())


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

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = notepadAdapter


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
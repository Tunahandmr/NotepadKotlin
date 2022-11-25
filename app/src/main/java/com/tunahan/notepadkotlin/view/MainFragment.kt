package com.tunahan.notepadkotlin.view

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.ListAdapter
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.databinding.FragmentMainBinding
import com.tunahan.notepadkotlin.model.Note
import com.tunahan.notepadkotlin.util.NoteTypes
import com.tunahan.notepadkotlin.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*
import kotlin.collections.ArrayList


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    var arrNotes = ArrayList<Note>()
    private lateinit var mNoteViewModel: NoteViewModel
    private lateinit var myAdapter: ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        //recyclerview


        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //viewmodel
        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerForContextMenu(binding.searchView)
        setupCustomSpinner()

        //searchView
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    searchNotes(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!=null){
                    searchNotes(newText)
                }
                return true
            }

        })

        binding.floatingActionButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    private fun searchNotes(query:String?){
        val searchQuery = "%$query%"

        val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

        mNoteViewModel.searchNote(searchQuery).observe(this) { list ->
            adapter.setData(list)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        MenuInflater(requireContext()).inflate(R.menu.pop_up_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteNoteMenu -> {
                println("xx")
            }

        }
        return super.onContextItemSelected(item)


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



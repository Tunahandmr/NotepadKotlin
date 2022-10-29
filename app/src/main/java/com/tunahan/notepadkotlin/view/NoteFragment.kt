package com.tunahan.notepadkotlin.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.tunahan.notepadkotlin.R
import com.tunahan.notepadkotlin.adapter.NoteArrayAdapter
import com.tunahan.notepadkotlin.databinding.FragmentNoteBinding
import com.tunahan.notepadkotlin.util.NoteTypes


class NoteFragment : Fragment(),MenuProvider {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedPicture: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCustomSpinner()
    }

    private fun setupCustomSpinner() {
        val adapter = NoteArrayAdapter(requireContext(), NoteTypes.Notes.list!!)
        binding.spinner.adapter = adapter
    }

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
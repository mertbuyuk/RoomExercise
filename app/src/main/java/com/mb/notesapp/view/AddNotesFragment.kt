package com.mb.notesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mb.notesapp.R
import com.mb.notesapp.databinding.FragmentAddNotesBinding
import com.mb.notesapp.infra.BaseFragment
import com.mb.notesapp.model.NoteModel
import com.mb.notesapp.repository.NoteRepository
import com.mb.notesapp.viewmodel.NoteViewModel
import com.mb.notesapp.viewmodel.NoteViewModelFactory

class AddNotesFragment : BaseFragment() {
    private var _binding : FragmentAddNotesBinding? = null
    private val binding get() = _binding!!

    override val viewModel: NoteViewModel by viewModels{
        NoteViewModelFactory(NoteRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.numberPickerPriority.minValue = 1
        binding.numberPickerPriority.maxValue = 10
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.addnote_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_note -> save()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save(){
        var title : String = binding.editTextTitle.text.toString()
        var desc : String = binding.editTextDescription.text.toString()
        var priority : String = binding.numberPickerPriority.value.toString()

        val note = NoteModel(_title = title,_description = desc,_priority = priority)

        if (note.title.isEmpty()||note.description.isEmpty()){
            Toast.makeText(requireContext(),"insert title and description",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(requireContext(),"added",Toast.LENGTH_SHORT).show()
            viewModel.addNotes(note)
            val action = AddNotesFragmentDirections.actionAddNotesFragmentToNotesFragment()
            findNavController().navigate(action)
        }
    }
}
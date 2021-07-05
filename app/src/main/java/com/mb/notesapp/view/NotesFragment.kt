package com.mb.notesapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mb.notesapp.adapter.NoteListAdapter
import com.mb.notesapp.databinding.FragmentNotesBinding
import com.mb.notesapp.infra.BaseFragment
import com.mb.notesapp.model.NoteModel
import com.mb.notesapp.repository.NoteRepository
import com.mb.notesapp.viewmodel.NoteViewModel
import com.mb.notesapp.viewmodel.NoteViewModelFactory

class NotesFragment : BaseFragment() {
    private var _binding : FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val adapter = NoteListAdapter(arrayListOf())

    override val viewModel: NoteViewModel by viewModels{
        NoteViewModelFactory(NoteRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())



        binding.fab.setOnClickListener{
            val action = NotesFragmentDirections.actionNotesFragmentToAddNotesFragment()
            findNavController().navigate(action)
        }

        viewModel.orderAndGetAll().observe(viewLifecycleOwner, Observer {
            adapter.noteList.clear()
            adapter.noteList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }
}
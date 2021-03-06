package com.mb.notesapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mb.notesapp.R
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

        setHasOptionsMenu(true)

        binding.fab.setOnClickListener{
            val action = NotesFragmentDirections.actionNotesFragmentToAddNotesFragment()
            findNavController().navigate(action)
        }

        viewModel.orderAndGetAll().observe(viewLifecycleOwner, Observer {
            adapter.noteList.clear()
            adapter.noteList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        var mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.deleteNote(adapter.noteList[viewHolder.adapterPosition])
                }
            }).attachToRecyclerView(binding.recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mainpage_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAll_item -> deleteNotes()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNotes() {
        val alert = AlertDialog.Builder(requireContext(),R.style.CustomDialog)

        alert.setTitle("Are you sure?")

        alert.setMessage("Do you want to delete all notes?")
        alert.setCancelable(false)
        alert.setIcon(R.drawable.ic_trash)

        alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            viewModel.deleteAll()
             }
        alert.setNegativeButton("No") {dialogInterface : DialogInterface, i:Int ->
            dialogInterface.cancel()
        }
        alert.show()
    }
}
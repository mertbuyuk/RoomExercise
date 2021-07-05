package com.mb.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mb.notesapp.R
import com.mb.notesapp.databinding.NoteItemBinding
import com.mb.notesapp.model.NoteModel
import org.w3c.dom.Text
import kotlin.coroutines.coroutineContext

class NoteListAdapter(val noteList : ArrayList<NoteModel>) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = NoteItemBinding.bind(view)

        val title : TextView = binding.txtTitle
        val description : TextView = binding.txtDescription
        val priority : TextView = binding.txtpriority
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = noteList[position].title
        holder.description.text = noteList[position].description
        holder.priority.text = noteList[position].priority.toString()
}

    override fun getItemCount(): Int = noteList.size
}
package com.geeks.noteapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.noteapp.data.models.Note
import com.geeks.noteapp.databinding.GridNoteHolderBinding
import com.geeks.noteapp.databinding.LinearNoteHolderBinding
import com.geeks.noteapp.utils.DiffCallback

class NoteAdapter(var viewType: Int) : ListAdapter<Note, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        const val VIEW_TYPE_LINEAR = 0
        const val VIEW_TYPE_GRID = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LINEAR) {
            LinearViewHolder(LinearNoteHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            GridViewHolder(GridNoteHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int) = viewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note = getItem(position)
        when(holder) {
            is LinearViewHolder -> holder.bind(note)
            is GridViewHolder -> holder.bind(note)
        }
    }

    inner class LinearViewHolder(private val binding : LinearNoteHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) = with(binding) {
            titleTextView.text = note.title
            textTextView.text = note.text
            dateTextView.text = note.dateString
        }
    }

    fun updateViewType(newViewType: Int) {
        viewType = newViewType
        notifyDataSetChanged()
    }

    inner class GridViewHolder(private val binding : GridNoteHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) = with(binding) {
            titleTextView.text = note.title
            textTextView.text = note.text
            dateTextView.text = note.dateString
        }
    }
}
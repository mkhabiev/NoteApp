package com.geeks.noteapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.geeks.noteapp.data.models.Note

class DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}
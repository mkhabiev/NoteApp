package com.geeks.noteapp.ui.fragments.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentNoteBinding
import com.geeks.noteapp.ui.adapters.NoteAdapter
import com.geeks.noteapp.utils.Preference

class NotesFragment : Fragment(){
    private lateinit var binding : FragmentNoteBinding
    private val preference = Preference()
    private lateinit var noteAdapter : NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()
    }

    private fun initialize() {
        preference.unit(requireContext())
        noteAdapter = NoteAdapter(preference.layoutManager)

        binding.notesRecyclerView.apply {
            if (preference.layoutManager == NoteAdapter.VIEW_TYPE_LINEAR) {
                layoutManager = LinearLayoutManager(requireContext())
                binding.shape.setImageResource(R.drawable.ic_grid_layout)
            } else {
                layoutManager = GridLayoutManager(requireContext(), 2)
                binding.shape.setImageResource(R.drawable.ic_linear_layout)
            }
            adapter = noteAdapter
        }
    }

    private fun setupListeners() = with(binding) {
        addButton.setOnClickListener{
            findNavController().navigate(R.id.action_notesFragment_to_detailFragment)
        }
        shape.setOnClickListener {
            if (noteAdapter.viewType == NoteAdapter.VIEW_TYPE_LINEAR) {
                binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                noteAdapter.updateViewType(NoteAdapter.VIEW_TYPE_GRID)
                preference.layoutManager = NoteAdapter.VIEW_TYPE_GRID
                binding.shape.setImageResource(R.drawable.ic_linear_layout)
            } else {
                binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                noteAdapter.updateViewType(NoteAdapter.VIEW_TYPE_LINEAR)
                preference.layoutManager = NoteAdapter.VIEW_TYPE_LINEAR
                binding.shape.setImageResource(R.drawable.ic_grid_layout)
            }
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){ notes ->
            binding.emptyNotesTextView.isVisible = notes.isEmpty()
            noteAdapter.submitList(notes)
        }
    }
}
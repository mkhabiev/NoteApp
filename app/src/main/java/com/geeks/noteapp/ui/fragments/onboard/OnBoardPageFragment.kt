package com.geeks.noteapp.ui.fragments.onboard

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geeks.noteapp.databinding.FragmentOnBoardPageBinding
import com.geeks.noteapp.models.OnBoardModel

class OnBoardPageFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        val onBoard = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("ON_BOARD", OnBoardModel::class.java)
        } else {
            arguments?.getSerializable("ON_BOARD") as? OnBoardModel
        }

        onBoard?.let { board ->
            animation.setAnimation(board.animation)
            titleTxt.text = board.title
            descTxt.text = board.desc
        }
    }
}
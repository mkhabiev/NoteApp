package com.geeks.noteapp.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geeks.noteapp.models.OnBoardModel
import com.geeks.noteapp.ui.fragments.onboard.OnBoardPageFragment

class OnBoardPageAdapter(
    private val list: List<OnBoardModel>,
    fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = OnBoardPageFragment().apply {
        val bundle = Bundle()
        bundle.putSerializable("ON_BOARD", list[position])
        arguments = bundle
    }
}
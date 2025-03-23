package com.geeks.noteapp.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.geeks.noteapp.R
import com.geeks.noteapp.ui.adapters.OnBoardPageAdapter
import com.geeks.noteapp.databinding.FragmentOnBoardBinding
import com.geeks.noteapp.models.OnBoardModel


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    private var OnBoardPageAdapter: OnBoardPageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun initialize() {
        OnBoardPageAdapter = OnBoardPageAdapter(generateDataOnBoard(), this@OnBoardFragment)
        binding.viewpager.adapter = OnBoardPageAdapter
    }

    fun generateDataOnBoard() = listOf<OnBoardModel>(
        OnBoardModel(
            R.raw.convenience_animation,
            "Удобство",
            "Создавайте заметки в два клика! Записывайте мысли, млеи и важные залачи моновення."
        ),
        OnBoardModel(
            R.raw.organization_animation,
            "Организация",
            "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
        ),
        OnBoardModel(
            R.raw.synchronization_animation,
            "Синхронизация",
            "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
        )
    )

    private fun setupListeners() {
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                changeActiveOnboardShower(position)
                binding.skip.visibility = if (position != 2) View.VISIBLE else View.INVISIBLE
                binding.startBtn.isVisible = position == 2
            }
        })
    }

    private fun changeActiveOnboardShower(position : Int) {
        val onBoardShowers = binding.onboardShowers;
        for (i in 0 until onBoardShowers.childCount) {
            val onBoardShower = onBoardShowers.getChildAt(i)
            if (i == position) {
                onBoardShower.setBackgroundResource(R.drawable.active_onboard_shower_circle)
            } else {
                onBoardShower.setBackgroundResource(R.drawable.onboard_shower_circle)
            }
        }
    }

}
package com.example.movies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movies.ui.fragments.PlaceholderFragment
import com.example.utilities.Constants.TAB_TITLES



class SectionsPagerAdapter(fragmentManager: FragmentManager,
                           lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position)
    }
}
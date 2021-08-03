package com.example.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.adapter.SectionsPagerAdapter
import com.example.movies.databinding.ActivityDashboardBinding
import com.example.utilities.Constants.TAB_TITLES

import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadTabLayout()
    }

    private fun loadTabLayout(){
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,lifecycle)

        with(binding){
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = getString(TAB_TITLES[position])
            }.attach()
        }
    }
}
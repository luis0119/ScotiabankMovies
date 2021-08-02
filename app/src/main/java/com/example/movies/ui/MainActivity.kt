package com.example.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.adapter.SectionsPagerAdapter
import com.example.utilities.Constants.TAB_TITLES
import com.example.movies.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadTabLayout()
    }

    private fun loadTabLayout(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager,lifecycle)

        with(binding){
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = getString(TAB_TITLES[position])
            }.attach()
        }
    }
}
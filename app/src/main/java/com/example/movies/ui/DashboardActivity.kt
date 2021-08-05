package com.example.movies.ui


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.movies.R
import com.example.movies.adapter.SectionsPagerAdapter
import com.example.movies.databinding.ActivityDashboardBinding
import com.example.movies.ui.fragments.MovieFragmentView
import com.example.utilities.Constants.TAB_TITLES
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var menuItem: MenuItem
    private lateinit var movieFragmentView: MovieFragmentView
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadTabLayout()
        loadToolbar()
    }

    private fun loadToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie, menu)
        menuItem = menu!!.findItem(R.id.action_search)
        searchView = menuItem.actionView as SearchView
        searchView?.setOnQueryTextListener(this)
        searchView?.setOnCloseListener(this)
        searchView?.maxWidth = Int.MAX_VALUE
        return true
    }

    private fun loadTabLayout() {
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = getString(TAB_TITLES[position])
            }.attach()
        }
    }

    fun setMovieFragment(movieFragmentView: MovieFragmentView) {
        this.movieFragmentView = movieFragmentView
    }

    fun closeSearch(){
       searchView?.let {
           it.setQuery("", false)
           it.clearFocus()
       }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        movieFragmentView.filter(newText)
        return false
    }

    override fun onClose(): Boolean {
        return false
    }
}
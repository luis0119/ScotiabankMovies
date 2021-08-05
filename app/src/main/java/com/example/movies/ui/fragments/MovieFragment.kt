package com.example.movies.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Movie
import com.example.movies.R
import com.example.movies.adapter.MovieRecyclerAdapter
import com.example.movies.databinding.FragmentMainBinding
import com.example.movies.ui.DashboardActivity
import com.example.movies.ui.MovieDetailActivity
import com.example.movies.viewModel.PageViewModel
import com.example.utilities.BaseFragment
import com.example.utilities.Constants.ARG_MOVIE
import com.example.utilities.Constants.ARG_SECTION_NUMBER
import com.example.utilities.Constants.DIRECTION_RECYCLER
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class MovieFragment : BaseFragment(), MovieFragmentView, MovieRecyclerAdapter.OnMoviesListener  {

    @Inject
    lateinit var pageViewModel: PageViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieRecyclerAdapter
    private val movies = ArrayList<Movie>()
    private lateinit var dashboardActivity : DashboardActivity
    private var scroll = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createProgressDialog()
        loadService()
    }

    override fun onResume() {
        super.onResume()
        loadFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        listener()
        loadRecycler()
        return binding.root
    }

    private fun loadFragment(){
        dashboardActivity = activity as DashboardActivity
        dashboardActivity.setMovieFragment(this)
    }

    private fun listener(){
        pageViewModel.movies.observe(viewLifecycleOwner, {
            movies.addAll(it)
            adapter.setMovies(it)
        })
        pageViewModel.message.observe(viewLifecycleOwner, {
            showAlertDialogInformation(R.string.title_user,it)
        })
        pageViewModel.progress.observe(viewLifecycleOwner, {
            if (it) {
                showProgressDIalog(R.string.wait)
            } else {
                dismissProgressDialog()
            }
        })
    }

    private fun loadRecycler() {
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = linearLayoutManager
        adapter = MovieRecyclerAdapter(movies,this.requireContext(),this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(DIRECTION_RECYCLER) && scroll) {
                    loadService()
                }
            }

        })
    }

    private fun loadService(){
        val section = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        pageViewModel.getMovies(section)
    }

    companion object {

        @JvmStatic
        fun newInstance(sectionNumber: Int): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        adapter.filter.filter("")
        dashboardActivity.closeSearch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun filter(text: String) {
        scroll = text.isEmpty()
        adapter.filter.filter(text)
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(ARG_MOVIE,movie)
        startActivity(intent)
    }
}
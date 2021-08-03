package com.example.movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movies.databinding.FragmentMainBinding
import com.example.movies.viewModel.PageViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class PlaceholderFragment : Fragment() {

    @Inject
    lateinit var pageViewModel: PageViewModel

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel.getMovies(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        listener()
        return  binding.root
    }

    private fun listener(){
        pageViewModel.movies.observe(viewLifecycleOwner, {
            print(it)
        })
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Movie
import com.example.movies.R
import com.example.movies.databinding.ItemMovieBinding
import com.example.utilities.Images
import com.example.utilities.RequestImage


class MovieRecyclerAdapter(
    private var movies: List<Movie>,
    private val context: Context,
    private val onMoviesListener: OnMoviesListener
) : RecyclerView.Adapter<MovieRecyclerAdapter.CustomViewHolder>(), Filterable {

    private var moviesFilter: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemMovieBinding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_movie
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.binding) {
            title.text = movie.title
            overview.text = movie.overview
            rating.text = movie.vote_average.toString()
            cardView.setOnClickListener {
                onMoviesListener.onItemClick(movie)
            }
            val requestImage =
                RequestImage(progress, logo, movie.poster_path, context, 450, 300)
            Images.loadImageURL(requestImage)
        }
    }


    fun setMovies(movies: List<Movie>) {
        this.moviesFilter = movies
        this.movies = movies
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemMovieBinding = itemView
    }

    interface OnMoviesListener {
        fun onItemClick(movie: Movie)
        fun isEmpty(visible: Int)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                movies = if (charString.isEmpty()) moviesFilter else {
                    val filteredList = ArrayList<Movie>()
                    moviesFilter
                        .filter {
                            it.title?.lowercase()?.contains(charString.lowercase()) == true
                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = movies }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                movies = if (results?.values == null){
                    ArrayList()
                }
                else{
                    results.values as List<Movie>
                }
                validateFilter(movies)
                notifyDataSetChanged()

            }
        }
    }

    fun validateFilter(movies :List<Movie>){
        if (movies.isEmpty()){
            onMoviesListener.isEmpty(View.VISIBLE)
        }else{
            onMoviesListener.isEmpty(View.INVISIBLE)
        }
    }

}
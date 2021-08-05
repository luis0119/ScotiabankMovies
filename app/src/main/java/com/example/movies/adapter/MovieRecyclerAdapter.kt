package com.example.movies.adapter

import android.content.Context
import android.graphics.drawable.Drawable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.domain.entity.Movie
import com.example.movies.R
import com.example.movies.databinding.ItemMovieBinding
import com.example.utilities.Constants.IMAGE_URL


class MovieRecyclerAdapter(private var movies: List<Movie>,
                           private val context :Context,
                           private val onMoviesListener: OnMoviesListener)
    : RecyclerView.Adapter<MovieRecyclerAdapter.CustomViewHolder>(), Filterable {

    private  var moviesFilter: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemMovieBinding = ItemMovieBinding.inflate(layoutInflater,parent,false)
        return CustomViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_movie
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item: ItemMovieBinding = holder.binding
        val movie = movies[position]
        item.title.text = movie.title
        item.cardView.setOnClickListener {
            onMoviesListener.onItemClick(movie)
        }
        loadImageURL(item.progress,item.logo,movie.poster_path)
    }

    private fun loadImageURL(progressBar: ProgressBar,imageView: ImageView, url: String?) {
        Glide.with(context)
            .load(IMAGE_URL + url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .override(500,0)
            .priority(Priority.HIGH)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.INVISIBLE
                    return isFirstResource
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.INVISIBLE
                    imageView.setImageDrawable(resource)
                    return isFirstResource
                }
            })
            .into(imageView)
    }

    fun setMovies(movies :List<Movie>){
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

                movies = if (results?.values == null)
                    ArrayList()
                else
                    results.values as List<Movie>
                notifyDataSetChanged()
            }
        }
    }
}
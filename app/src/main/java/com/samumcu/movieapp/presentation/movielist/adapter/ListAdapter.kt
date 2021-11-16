package com.samumcu.movieapp.presentation.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.samumcu.movieapp.R
import com.samumcu.movieapp.data.retrofit.response.MovieResponse
import com.samumcu.movieapp.databinding.ListItemRowBinding
import com.samumcu.movieapp.databinding.MovieDetailFragmentBinding
import com.samumcu.movieapp.presentation.movielist.MovieListFragmentDirections
import com.samumcu.movieapp.utils.UIUtils

class ListAdapter(
    var movies: List<MovieResponse>
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemRowBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(val binding: ListItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponse) {
            with(binding) {
                movieData = movie
                row.setOnClickListener {
                    val direction =
                        MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie.id!!)
                    itemView.findNavController().navigate(direction)
                }
            }
        }
    }
}
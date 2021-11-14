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
import com.samumcu.movieapp.presentation.movielist.MovieListFragmentDirections
import com.samumcu.movieapp.utils.UIUtils

class ListAdapter(
    var movies: List<MovieResponse>
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_row,
            parent,
            false
        )
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.rowLayout.setOnClickListener {
            val direction =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movies[position].id!!)
            holder.itemView.findNavController().navigate(direction)

        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.image)
        private val title = view.findViewById<TextView>(R.id.title)
        private val subTitle = view.findViewById<TextView>(R.id.subTitle)
        private val date = view.findViewById<TextView>(R.id.date)
        val rowLayout = view.findViewById<ConstraintLayout>(R.id.row)

        fun bind(movie: MovieResponse) {
            title.text = movie.title
            subTitle.text = movie.overview
            date.text = movie.releaseDate
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w300/"+movie.backdropPath)
                .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)

        }
    }
}
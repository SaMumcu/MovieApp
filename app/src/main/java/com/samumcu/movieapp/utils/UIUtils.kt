package com.samumcu.movieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.samumcu.movieapp.R

object UIUtils {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImageWithGlide(path: String?) {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w300/"+path)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}
package com.samumcu.movieapp.utils

import android.os.Build
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.bold
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

    @JvmStatic
    fun formatDate(date: String?): String {
        return date?.replace("-",".").toString()
    }

    @JvmStatic
    fun formatRate(text: String?): Spanned {
        val formatted = HtmlCompat.fromHtml("<b><font color='#000000'>"+text+"</font></b>"+"/10", HtmlCompat.FROM_HTML_MODE_LEGACY)
        return formatted
    }
}
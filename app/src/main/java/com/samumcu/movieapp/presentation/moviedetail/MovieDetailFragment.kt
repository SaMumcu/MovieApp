package com.samumcu.movieapp.presentation.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.samumcu.movieapp.R
import com.samumcu.movieapp.databinding.MovieDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private val args by navArgs<MovieDetailFragmentArgs>()
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        viewModel.getMovieDetailList(args.movieId)
        observeMovieDetailLiveData()
        return binding.root
    }

    private fun observeMovieDetailLiveData() {
        viewModel.movieDetailLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val details = it
                binding.movieDetail = details
                Timber.d(details.toString())
            }
        })
    }
}
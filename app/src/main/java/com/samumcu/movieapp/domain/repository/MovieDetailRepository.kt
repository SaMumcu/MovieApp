package com.samumcu.movieapp.domain.repository

import com.samumcu.movieapp.data.retrofit.service.MovieService
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getMovieDetail(id: Int) =
        movieService.getMovieDetails(id)
}
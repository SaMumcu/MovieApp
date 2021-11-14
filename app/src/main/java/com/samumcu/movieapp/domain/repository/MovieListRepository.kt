package com.samumcu.movieapp.domain.repository

import com.samumcu.movieapp.data.retrofit.service.MovieService
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getNowPlayingMovies(page: String) =
        movieService.getNowPlayingMovies(page)

    suspend fun getUpcomingMovies(page: String) =
        movieService.getUpcomingMovies(page)
}
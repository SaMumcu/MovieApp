package com.samumcu.movieapp.domain.usecase

import com.samumcu.movieapp.data.retrofit.response.MoviesPageResponse
import com.samumcu.movieapp.domain.repository.MovieListRepository
import com.samumcu.movieapp.utils.NetworkResult
import java.io.IOException
import javax.inject.Inject

class MovieListUseCase @Inject constructor(
    private val movieListRepository: MovieListRepository
) {
    suspend fun execute(page: String): NetworkResult<MoviesPageResponse> = try {
        movieListRepository.getUpcomingMovies(
            page
        ).let {
            NetworkResult.Success(it)
        }
    } catch (e: IOException) {
        NetworkResult.Error(e.toString())
    }
}
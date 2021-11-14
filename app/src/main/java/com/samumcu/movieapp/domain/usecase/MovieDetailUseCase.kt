package com.samumcu.movieapp.domain.usecase

import com.samumcu.movieapp.data.retrofit.response.MovieDetailsResponse
import com.samumcu.movieapp.domain.repository.MovieDetailRepository
import com.samumcu.movieapp.utils.NetworkResult
import java.io.IOException
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) {
    suspend fun execute(page: Int): NetworkResult<MovieDetailsResponse> = try {
        movieDetailRepository.getMovieDetail(
            page
        ).let {
            NetworkResult.Success(it)
        }
    } catch (e: IOException) {
        NetworkResult.Error(e.toString())
    }
}
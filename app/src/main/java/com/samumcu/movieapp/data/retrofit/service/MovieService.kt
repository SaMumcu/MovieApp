package com.samumcu.movieapp.data.retrofit.service

import com.samumcu.movieapp.data.retrofit.response.MovieDetailsResponse
import com.samumcu.movieapp.data.retrofit.response.MoviesPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: String): MoviesPageResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: String): MoviesPageResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailsResponse
}
package com.samumcu.movieapp.core

import com.samumcu.movieapp.data.retrofit.service.MovieService
import com.samumcu.movieapp.domain.repository.MovieDetailRepository
import com.samumcu.movieapp.domain.repository.MovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun providesMovieRepo(movieService: MovieService): MovieListRepository {
        return MovieListRepository(movieService)
    }

    @Provides
    fun providesMovieDetailRepo(movieService: MovieService): MovieDetailRepository {
        return MovieDetailRepository(movieService)
    }
}
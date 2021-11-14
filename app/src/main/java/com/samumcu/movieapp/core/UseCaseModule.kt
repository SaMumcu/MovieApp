package com.samumcu.movieapp.core

import com.samumcu.movieapp.domain.repository.MovieDetailRepository
import com.samumcu.movieapp.domain.repository.MovieListRepository
import com.samumcu.movieapp.domain.usecase.MovieDetailUseCase
import com.samumcu.movieapp.domain.usecase.MovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    fun providesMovieListUseCase(movieListRepository: MovieListRepository): MovieListUseCase {
        return MovieListUseCase(movieListRepository)
    }

    @Provides
    fun providesMovieDetailUseCase(movieDetailRepository: MovieDetailRepository): MovieDetailUseCase {
        return MovieDetailUseCase(movieDetailRepository)
    }
}
package com.samumcu.movieapp.presentation.movielist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samumcu.movieapp.data.retrofit.response.MoviesPageResponse
import com.samumcu.movieapp.domain.usecase.MovieListUseCase
import com.samumcu.movieapp.domain.usecase.MoviePagerListUseCase
import com.samumcu.movieapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase,
    private val moviePagerListUseCase: MoviePagerListUseCase
) : ViewModel() {

    private val movieListData = MutableLiveData<MoviesPageResponse?>()
    val movieListDataLiveData: LiveData<MoviesPageResponse?> get() = movieListData

    private val moviePagerListData = MutableLiveData<MoviesPageResponse?>()
    val moviePagerListDataLiveData: LiveData<MoviesPageResponse?> get() = moviePagerListData
    var currentPage = 1
    var totalPages = 1

    fun getPagerList() {
        viewModelScope.launch {
            moviePagerListUseCase.execute(
                currentPage.toString()
            ).also {
                when {
                    it is NetworkResult.Success -> {
                        moviePagerListData.value = it.data
                        it.data?.totalPages?.let {
                            totalPages = it
                        }
                    }
                    it is NetworkResult.Error ->
                        print(it.message)
                }
            }
        }
    }

    fun getMovieList() {
        viewModelScope.launch {
            movieListUseCase.execute(
                currentPage.toString()
            ).also {
                when {
                    it is NetworkResult.Success -> {
                        movieListData.value = it.data
                    }
                    it is NetworkResult.Error ->
                        print(it.message)
                }
            }
        }
    }

    fun getPageData() {
        getPagerList()
        getMovieList()
    }

    fun goToPreviousPage() {
        currentPage--
    }

    fun goToNextPage() {
        currentPage++
    }
}
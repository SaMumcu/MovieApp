package com.samumcu.movieapp.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samumcu.movieapp.data.retrofit.response.MovieDetailsResponse
import com.samumcu.movieapp.domain.usecase.MovieDetailUseCase
import com.samumcu.movieapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase
) : ViewModel() {

    private val movieDetailData = MutableLiveData<MovieDetailsResponse?>()
    val movieDetailLiveData: LiveData<MovieDetailsResponse?> get() = movieDetailData

    fun getMovieDetailList(page: Int) {
        viewModelScope.launch {
            movieDetailUseCase.execute(
                page
            ).also {
                when {
                    it is NetworkResult.Success -> {
                        movieDetailData.value = it.data
                    }
                    it is NetworkResult.Error ->
                        print(it.message)
                }
            }
        }
    }
}
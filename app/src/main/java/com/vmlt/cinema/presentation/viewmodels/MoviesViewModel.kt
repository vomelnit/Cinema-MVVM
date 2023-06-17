package com.vmlt.cinema.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.usecases.GetEntireMovieBasicInfoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val getEntireMovieBasicInfoListUseCase: GetEntireMovieBasicInfoListUseCase) :
    ViewModel() {

    private val _movieBasicInfoList = MutableLiveData<List<MovieBasicInfo>>()
    val movieBasicInfoList: LiveData<List<MovieBasicInfo>> = _movieBasicInfoList

    fun getAvailableMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            updateMovieList(getEntireMovieBasicInfoListUseCase.execute())
        }
    }

    private suspend fun updateMovieList(movieList: List<MovieBasicInfo>) =
        withContext(Dispatchers.Main) {
            _movieBasicInfoList.value = movieList
        }

}
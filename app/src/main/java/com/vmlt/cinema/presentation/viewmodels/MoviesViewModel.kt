package com.vmlt.cinema.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmlt.cinema.domain.entities.MovieBasicInfo
import com.vmlt.cinema.domain.usecases.GetEntireMovieBasicInfoListUseCase

class MoviesViewModel(private val getEntireMovieBasicInfoListUseCase: GetEntireMovieBasicInfoListUseCase) :
    ViewModel() {

    private val _movieBasicInfoList = MutableLiveData<List<MovieBasicInfo>>()
    val movieBasicInfoList: LiveData<List<MovieBasicInfo>> = _movieBasicInfoList

    fun getAvailableMovieList() {
        _movieBasicInfoList.value = getEntireMovieBasicInfoListUseCase.execute()
    }

}
package com.vmlt.cinema.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.usecases.GetMovieDetailsByIdUseCase

class DetailsViewModel(private val getMovieDetailsByIdUseCase: GetMovieDetailsByIdUseCase) :
    ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails

    fun getMovieDetails(movieId: Int) {
        _movieDetails.value = getMovieDetailsByIdUseCase.execute(movieId)
    }
}
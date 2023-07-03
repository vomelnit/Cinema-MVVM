package com.vmlt.cinema.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.domain.usecases.GetMovieDetailsByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val getMovieDetailsByIdUseCase: GetMovieDetailsByIdUseCase) :
    ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetails: Movie? = getMovieDetailsByIdUseCase.execute(movieId)
            movieDetails?.let { movieDetails ->
                updateMovieDetails(movieDetails)
            }
        }
    }

    private suspend fun updateMovieDetails(movieDetails: Movie) = withContext(Dispatchers.Main) {
        _movieDetails.value = movieDetails
    }
}
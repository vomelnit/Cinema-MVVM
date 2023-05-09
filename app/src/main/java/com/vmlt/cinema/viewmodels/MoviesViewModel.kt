package com.vmlt.cinema.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmlt.cinema.utils.MovieUtils
import com.vmlt.cinema.model.Movie
import com.vmlt.cinema.model.MovieBasicInfo

class MoviesViewModel(private val movieModel: MovieUtils = MovieUtils) : ViewModel() {
    private val _movieBasicInfoList = MutableLiveData<List<MovieBasicInfo>>()
    val movieBasicInfoList: LiveData<List<MovieBasicInfo>> = _movieBasicInfoList

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails

    private val _movieName = MutableLiveData<String>()
    val movieName: LiveData<String> = _movieName

    fun getAvailableMovieList() {
        _movieBasicInfoList.value = movieModel.getAllAvailableMoviesBasicInfo()
    }

    fun getMovieDetails(movieId: Int) {
        _movieDetails.value = movieModel.getMovieInfoById(movieId)
    }

    fun getMovieName(movieId: Int) {
        _movieName.value = movieModel.getMovieNameById(movieId)
    }
}
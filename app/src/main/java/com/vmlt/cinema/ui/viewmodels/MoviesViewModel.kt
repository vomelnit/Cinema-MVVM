package com.vmlt.cinema.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmlt.cinema.utils.MovieUtils
import com.vmlt.cinema.utils.model.Movie
import com.vmlt.cinema.utils.model.MovieBasicInfo

class MoviesViewModel(private val movieModel: MovieUtils = MovieUtils) : ViewModel() {
    private val movieBasicInfoList = MutableLiveData<List<MovieBasicInfo>>()
    private val movieDetails = MutableLiveData<Movie>()

    fun getAvailableMovieList(): LiveData<List<MovieBasicInfo>> {
        movieBasicInfoList.value = movieModel.getAllAvailableMoviesBasicInfo()
        return movieBasicInfoList
    }

    fun getMovieDetails(movieId: Int): LiveData<Movie> {
        movieDetails.value = movieModel.getMovieInfoById(movieId)
        return movieDetails
    }
}
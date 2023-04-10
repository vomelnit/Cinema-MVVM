package com.vmlt.cinema.ui.details

import com.vmlt.cinema.utils.model.Movie

interface MovieDetailsContract {
    interface DetailsView {
        fun updateMovieRating(movieRating: Float)
    }

    interface DetailsPresenter {
        fun getMovieDetails(movieId: Int): Movie?
        fun refreshMovieInfo(movieId: Int): Movie?
        fun startActivityForTickets(movieId: Int)
    }
}
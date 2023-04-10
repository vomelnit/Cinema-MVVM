package com.vmlt.cinema.ui.main

import com.vmlt.cinema.utils.model.MovieBasicInfo

interface MainContract {
    interface MainView {
        fun updateMovieList()
    }

    interface MainPresenter {
        fun getEntireMovieList(): List<MovieBasicInfo>
    }
}
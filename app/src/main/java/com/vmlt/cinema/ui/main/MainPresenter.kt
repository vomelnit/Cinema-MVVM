package com.vmlt.cinema.ui.main

import com.vmlt.cinema.utils.model.MovieBasicInfo
import com.vmlt.cinema.utils.MovieUtils
import java.lang.ref.WeakReference

class MainPresenter(
    private val mainView: MainContract.MainView,
    private val movieModel: MovieUtils
) :
    MainContract.MainPresenter {

    val mainViewReference: WeakReference<MainContract.MainView> = WeakReference(mainView)

    override fun getEntireMovieList(): List<MovieBasicInfo> {
        return movieModel.getAllAvailableMoviesBasicInfo()
    }

}
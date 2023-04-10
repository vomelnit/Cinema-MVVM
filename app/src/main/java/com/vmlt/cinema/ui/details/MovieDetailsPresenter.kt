package com.vmlt.cinema.ui.details

import android.content.Context
import android.content.Intent
import com.vmlt.cinema.utils.model.Movie
import com.vmlt.cinema.utils.MovieUtils
import com.vmlt.cinema.ui.tickets.TicketsActivity
import java.lang.ref.WeakReference

class MovieDetailsPresenter(
    private val detailsView: MovieDetailsContract.DetailsView,
    private val context: Context,
    private val movieModel: MovieUtils
) :
    MovieDetailsContract.DetailsPresenter {

    val detailsViewReference: WeakReference<MovieDetailsContract.DetailsView> =
        WeakReference(detailsView)

    override fun getMovieDetails(movieId: Int): Movie? {
        return movieModel.getMovieInfoById(movieId)
    }

    override fun refreshMovieInfo(movieId: Int): Movie? {
        return movieModel.getMovieInfoById(movieId)
    }

    override fun startActivityForTickets(movieId: Int) {
        val intent = Intent(context, TicketsActivity::class.java)
        intent.putExtra("MovieId", movieId)
        context.startActivity(intent)
    }
}
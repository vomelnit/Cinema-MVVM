package com.vmlt.cinema.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.vmlt.cinema.utils.model.Movie
import com.vmlt.cinema.utils.MovieUtils
import com.vmlt.cinema.R

class MovieDetailsActivity : ComponentActivity(), MovieDetailsContract.DetailsView,
    View.OnClickListener {

    private lateinit var detailsPresenter: MovieDetailsContract.DetailsPresenter
    private lateinit var movieRatingText: TextView
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = intent.getIntExtra("MovieId", -1)
        Log.i("MovieDetailsActivity", "movieId = $movieId");

        detailsPresenter = MovieDetailsPresenter(this, this, MovieUtils)
        val movieDetails: Movie? = detailsPresenter.getMovieDetails(movieId)

        if (movieDetails == null) {
            setContentView(R.layout.issue_layout)
            return
        }

        setContentView(R.layout.activity_movie_details)


        val moviePosterImage: ImageView = findViewById(R.id.movie_poster_image)
        movieRatingText = findViewById(R.id.movie_rating_text)
        val movieNameText: TextView = findViewById(R.id.movie_name_text)
        val movieYearText: TextView = findViewById(R.id.movie_year_text)
        val movieGenreText: TextView = findViewById(R.id.movie_genre_text)

        moviePosterImage.setImageResource(movieDetails.iconId)
        movieNameText.text = movieDetails.name
        movieRatingText.text = movieDetails.rating.toString()
        movieYearText.text = movieDetails.year.toString()
        movieGenreText.text = movieDetails.genre

        val buyTicketBtn: Button = findViewById(R.id.buy_ticket_btn)
        buyTicketBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buy_ticket_btn -> {
                detailsPresenter.startActivityForTickets(movieId)
            }
        }
    }

    override fun updateMovieRating(movieRating: Float) {
        movieRatingText.text = movieRating.toString()
    }
}
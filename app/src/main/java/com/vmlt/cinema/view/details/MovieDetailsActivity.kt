package com.vmlt.cinema.view.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vmlt.cinema.R
import com.vmlt.cinema.view.tickets.TicketsActivity
import com.vmlt.cinema.viewmodels.MoviesViewModel
import com.vmlt.cinema.model.Movie

const val INTENT_EXTRA_MOVIE_ID_KEY = "MovieId"

class MovieDetailsActivity : ComponentActivity() {
    private lateinit var moviesViewModel: MoviesViewModel

    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieId = intent.getIntExtra(INTENT_EXTRA_MOVIE_ID_KEY, -1)
        Log.i("MovieDetailsActivity", "movieId = $movieId");

        setupViewModel()
    }

    private fun setupViewModel() {
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        moviesViewModel.movieDetails.observe(this, Observer { movie ->
            setMovieDetailsContent(movie)
        })

        moviesViewModel.getMovieDetails(movieId)
    }

    private fun setMovieDetailsContent(movieDetails: Movie?) {
        val movie = movieDetails ?: return

        val moviePosterImage: ImageView = findViewById(R.id.movie_poster_image)
        val movieRatingText: TextView = findViewById(R.id.movie_rating_text)
        val movieNameText: TextView = findViewById(R.id.movie_name_text)
        val movieYearText: TextView = findViewById(R.id.movie_year_text)
        val movieGenreText: TextView = findViewById(R.id.movie_genre_text)
        val buyTicketBtn: Button = findViewById(R.id.buy_ticket_btn)

        moviePosterImage.setImageResource(movie.iconId)
        movieNameText.text = movie.name
        movieRatingText.text = movie.rating.toString()
        movieYearText.text = movie.year.toString()
        movieGenreText.text = movie.genre

        buyTicketBtn.setOnClickListener {
            startActivityForTickets(movieId)
        }
    }

    private fun startActivityForTickets(movieId: Int) {
        val intent = Intent(this, TicketsActivity::class.java)
        intent.putExtra(INTENT_EXTRA_MOVIE_ID_KEY, movieId)
        startActivity(intent)
    }
}
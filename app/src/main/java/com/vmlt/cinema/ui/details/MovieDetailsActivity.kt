package com.vmlt.cinema.ui.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vmlt.cinema.R
import com.vmlt.cinema.ui.tickets.TicketsActivity
import com.vmlt.cinema.ui.viewmodels.MoviesViewModel
import com.vmlt.cinema.utils.model.Movie

class MovieDetailsActivity : ComponentActivity(), View.OnClickListener {
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var movieRatingText: TextView
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = intent.getIntExtra("MovieId", -1)
        Log.i("MovieDetailsActivity", "movieId = $movieId");

        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        moviesViewModel.getMovieDetails(movieId).observe(this, Observer { movie ->
            setMovieDetailsContent(movie)
        })
    }

    private fun setMovieDetailsContent(movieDetails: Movie) {
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
                startActivityForTickets(movieId)
            }
        }
    }

    private fun startActivityForTickets(movieId: Int) {
        val intent = Intent(this, TicketsActivity::class.java)
        intent.putExtra("MovieId", movieId)
        startActivity(intent)
    }
}
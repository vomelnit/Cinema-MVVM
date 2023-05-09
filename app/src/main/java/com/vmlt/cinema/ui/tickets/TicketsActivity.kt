package com.vmlt.cinema.ui.tickets

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vmlt.cinema.R
import com.vmlt.cinema.ui.viewmodels.MoviesViewModel
import com.vmlt.cinema.ui.viewmodels.TicketsViewModel

class TicketsActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var ticketsViewModel: TicketsViewModel
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var ticketsAmountText: TextView
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = intent.getIntExtra("MovieId", -1)
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        ticketsViewModel = ViewModelProvider(this).get(TicketsViewModel::class.java)
        Log.i("TicketsActivity", "movieId = $movieId");
        setContentView(R.layout.activity_tickets)

        val movieNameText: TextView = findViewById(R.id.movie_name_text)
        moviesViewModel.getMovieDetails(movieId).observe(this, Observer { movie ->
            movieNameText.text = movie.name
        })
        ticketsAmountText = findViewById(R.id.available_tickets_amount_text)

        ticketsViewModel.getTicketsAmount(movieId).observe(this, Observer { ticketsAmount ->
            ticketsAmount.let { ticketsAmountText.text = ticketsAmount.toString() }
        })

        val buyTicketBtn: Button = findViewById(R.id.buy_ticket_btn)
        val returnTicketBtn: Button = findViewById(R.id.return_ticket_btn)
        buyTicketBtn.setOnClickListener(this)
        returnTicketBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buy_ticket_btn -> {
                ticketsViewModel.buyTicketFromCinema(movieId)
            }
            R.id.return_ticket_btn -> {
                ticketsViewModel.returnTicketToCinema(movieId)
            }
        }
    }
}
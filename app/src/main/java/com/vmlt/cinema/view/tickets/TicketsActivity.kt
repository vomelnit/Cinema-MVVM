package com.vmlt.cinema.view.tickets

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vmlt.cinema.R
import com.vmlt.cinema.view.details.INTENT_EXTRA_MOVIE_ID_KEY
import com.vmlt.cinema.viewmodels.MoviesViewModel
import com.vmlt.cinema.viewmodels.TicketsViewModel

class TicketsActivity : ComponentActivity() {
    private lateinit var movieNameText: TextView
    private lateinit var ticketsAmountText: TextView

    private lateinit var ticketsViewModel: TicketsViewModel
    private lateinit var moviesViewModel: MoviesViewModel

    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        movieId = intent.getIntExtra(INTENT_EXTRA_MOVIE_ID_KEY, -1)
        Log.i("TicketsActivity", "movieId = $movieId");

        setupUi()
        setupViewModel()
    }

    private fun setupUi() {
        movieNameText = findViewById(R.id.movie_name_text)
        ticketsAmountText = findViewById(R.id.available_tickets_amount_text)

        val buyTicketBtn: Button = findViewById(R.id.buy_ticket_btn)
        val returnTicketBtn: Button = findViewById(R.id.return_ticket_btn)

        buyTicketBtn.setOnClickListener {
            ticketsViewModel.buyTicketFromCinema(movieId)
        }
        returnTicketBtn.setOnClickListener {
            ticketsViewModel.returnTicketToCinema(movieId)
        }
    }

    private fun setupViewModel() {
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        moviesViewModel.movieName.observe(this, Observer { name ->
            if (name != null) {
                movieNameText.text = name
            }
        })
        moviesViewModel.getMovieName(movieId)

        ticketsViewModel = ViewModelProvider(this).get(TicketsViewModel::class.java)

        ticketsViewModel.ticketsAmount.observe(this, Observer { ticketsAmount ->
            ticketsAmount.let {
                ticketsAmountText.text = ticketsAmount.toString()
            }
        })
        ticketsViewModel.updateTicketsAmount(movieId)
    }
}
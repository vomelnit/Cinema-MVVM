package com.vmlt.cinema.ui.tickets

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.vmlt.cinema.R
import com.vmlt.cinema.utils.MovieUtils
import com.vmlt.cinema.utils.TicketsUtils

class TicketsActivity : ComponentActivity(), TicketsContract.TicketsView, View.OnClickListener {

    private lateinit var ticketsPresenter: TicketsContract.TicketsPresenter
    private lateinit var ticketsAmountText: TextView
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = intent.getIntExtra("MovieId", -1)
        ticketsPresenter = TicketsPresenter(this, MovieUtils, TicketsUtils)
        Log.i("TicketsActivity", "movieId = $movieId");
        setContentView(R.layout.activity_tickets)

        val movieNameText: TextView = findViewById(R.id.movie_name_text)
        movieNameText.text = ticketsPresenter.getMovieName(movieId)
        ticketsAmountText = findViewById(R.id.available_tickets_amount_text)
        ticketsAmountText.text = ticketsPresenter.getMovieTickets(movieId).toString()

        val buyTicketBtn: Button = findViewById(R.id.buy_ticket_btn)
        val returnTicketBtn: Button = findViewById(R.id.return_ticket_btn)
        buyTicketBtn.setOnClickListener(this)
        returnTicketBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buy_ticket_btn -> {
                ticketsPresenter.buyTicketFromCinema(movieId)
            }
            R.id.return_ticket_btn -> {
                ticketsPresenter.returnTicketToCinema(movieId)
            }
        }
    }


    override fun updateAvailableTicketsAmount(ticketsAmount: Int) {
        ticketsAmountText.text = ticketsAmount.toString()
    }

}
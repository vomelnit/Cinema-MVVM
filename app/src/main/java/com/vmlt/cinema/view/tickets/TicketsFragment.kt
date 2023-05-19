package com.vmlt.cinema.view.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vmlt.cinema.R
import com.vmlt.cinema.view.details.INTENT_EXTRA_MOVIE_ID_KEY
import com.vmlt.cinema.viewmodels.MoviesViewModel
import com.vmlt.cinema.viewmodels.TicketsViewModel

class TicketsFragment : Fragment() {
    private var movieNameText: TextView? = null
    private var ticketsAmountText: TextView? = null

    private lateinit var ticketsViewModel: TicketsViewModel
    private lateinit var moviesViewModel: MoviesViewModel

    private var movieId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_tickets,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieId = arguments?.getInt(INTENT_EXTRA_MOVIE_ID_KEY) ?: -1

        setupUi(view)
        setupViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupUi(rootView: View) {
        movieNameText = rootView.findViewById(R.id.movie_name_text)
        ticketsAmountText = rootView.findViewById(R.id.available_tickets_amount_text)

        val buyTicketBtn: Button? = rootView.findViewById(R.id.buy_ticket_btn)
        val returnTicketBtn: Button? = rootView.findViewById(R.id.return_ticket_btn)

        buyTicketBtn?.setOnClickListener {
            ticketsViewModel.buyTicketFromCinema(movieId)
        }
        returnTicketBtn?.setOnClickListener {
            ticketsViewModel.returnTicketToCinema(movieId)
        }
    }

    private fun setupViewModel() {
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        moviesViewModel.movieName.observe(viewLifecycleOwner, Observer { name ->
            if (name != null) {
                movieNameText?.text = name
            }
        })
        moviesViewModel.getMovieName(movieId)

        ticketsViewModel = ViewModelProvider(this)[TicketsViewModel::class.java]

        ticketsViewModel.ticketsAmount.observe(viewLifecycleOwner, Observer { ticketsAmount ->
            ticketsAmount.let {
                ticketsAmountText?.text = ticketsAmount.toString()
            }
        })
        ticketsViewModel.updateTicketsAmount(movieId)
    }
}
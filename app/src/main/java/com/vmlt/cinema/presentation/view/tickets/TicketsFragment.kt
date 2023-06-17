package com.vmlt.cinema.presentation.view.tickets

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
import com.vmlt.cinema.data.db.CinemaDatabase
import com.vmlt.cinema.domain.usecases.BuyTicketByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.GetAvailableTicketsAmountByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.ReturnTicketByMovieIdUseCase
import com.vmlt.cinema.presentation.view.details.INTENT_EXTRA_MOVIE_ID_KEY
import com.vmlt.cinema.presentation.viewmodels.TicketsViewModel
import com.vmlt.cinema.presentation.viewmodels.factories.TicketsViewModelFactory

class TicketsFragment : Fragment() {
    private var movieNameText: TextView? = null
    private var ticketsAmountText: TextView? = null

    private lateinit var ticketsViewModel: TicketsViewModel
    private lateinit var getTicketsAmountUseCase: GetAvailableTicketsAmountByMovieIdUseCase
    private lateinit var buyTicketUseCase: BuyTicketByMovieIdUseCase
    private lateinit var returnTicketUseCase: ReturnTicketByMovieIdUseCase

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

        setupRepository()
        setupUi(view)
        setupViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupRepository() {
        context?.let {
            val movieCacheRepository =
                com.vmlt.cinema.data.repository.MovieCacheImpl(CinemaDatabase.getDatabase(context = it))
            val movieRepository =
                com.vmlt.cinema.data.repository.MovieRepositoryImpl(movieCacheRepository)
            getTicketsAmountUseCase = GetAvailableTicketsAmountByMovieIdUseCase(movieRepository)
            buyTicketUseCase = BuyTicketByMovieIdUseCase(movieRepository)
            returnTicketUseCase = ReturnTicketByMovieIdUseCase(movieRepository)
        }

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
        ticketsViewModel = ViewModelProvider(
            this,
            TicketsViewModelFactory(
                getTicketsAmountUseCase,
                buyTicketUseCase,
                returnTicketUseCase,
                this
            )
        )[TicketsViewModel::class.java]

        ticketsViewModel.movieName.observe(viewLifecycleOwner, Observer { name ->
            if (name != null) {
                movieNameText?.text = name
            }
        })

        ticketsViewModel = ViewModelProvider(this)[TicketsViewModel::class.java]

        ticketsViewModel.ticketsAmount.observe(viewLifecycleOwner, Observer { ticketsAmount ->
            ticketsAmount.let {
                ticketsAmountText?.text = ticketsAmount.toString()
            }
        })

        ticketsViewModel.refreshScreenInfo(movieId)
    }
}
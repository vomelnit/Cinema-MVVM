package com.vmlt.cinema.presentation.view.tickets

import android.content.Context
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
import com.vmlt.cinema.presentation.view.details.INTENT_EXTRA_MOVIE_ID_KEY
import com.vmlt.cinema.presentation.view.MainActivity
import com.vmlt.cinema.presentation.viewmodels.TicketsViewModel
import com.vmlt.cinema.presentation.viewmodels.factories.CustomViewModelFactory
import javax.inject.Inject

class TicketsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    lateinit var ticketsViewModel: TicketsViewModel

    private var movieNameText: TextView? = null
    private var ticketsAmountText: TextView? = null

    private var movieId: Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).movieListComponent.inject(this)
        ticketsViewModel = ViewModelProvider(this, viewModelFactory).get(TicketsViewModel::class.java)
    }

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
        ticketsViewModel.movieName.observe(viewLifecycleOwner, Observer { name ->
            if (name != null) {
                movieNameText?.text = name
            }
        })

        ticketsViewModel.ticketsAmount.observe(viewLifecycleOwner, Observer { ticketsAmount ->
            ticketsAmount.let {
                ticketsAmountText?.text = ticketsAmount.toString()
            }
        })

        ticketsViewModel.refreshScreenInfo(movieId)
    }
}
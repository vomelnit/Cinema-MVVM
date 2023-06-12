package com.vmlt.cinema.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmlt.cinema.domain.entities.TicketsInfo
import com.vmlt.cinema.domain.usecases.BuyTicketByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.GetAvailableTicketsAmountByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.ReturnTicketByMovieIdUseCase

class TicketsViewModel(
    private val getAvailableTicketsAmountByMovieIdUseCase: GetAvailableTicketsAmountByMovieIdUseCase,
    private val buyTicketByMovieIdUseCase: BuyTicketByMovieIdUseCase,
    private val returnTicketByMovieIdUseCase: ReturnTicketByMovieIdUseCase
) : ViewModel() {
    private val _ticketsAmount = MutableLiveData<Int>()
    val ticketsAmount: LiveData<Int> = _ticketsAmount

    private val _movieName = MutableLiveData<String>()
    val movieName: LiveData<String> = _movieName

    fun buyTicketFromCinema(movieId: Int) {
        buyTicketByMovieIdUseCase.execute(movieId)
        updateTicketsAmount(movieId)
    }

    fun returnTicketToCinema(movieId: Int) {
        returnTicketByMovieIdUseCase.execute(movieId)
        updateTicketsAmount(movieId)
    }

    fun updateTicketsAmount(movieId: Int) {
        val ticketsInfo: TicketsInfo? = getAvailableTicketsAmountByMovieIdUseCase.execute(movieId)
        ticketsInfo?.let {
            _ticketsAmount.value = it.ticketsAmount
        } ?: run {
            _ticketsAmount.value = -1
            _movieName.value = "Not found"
        }
    }

    fun getMovieName(movieId: Int) {
        val ticketsInfo: TicketsInfo? = getAvailableTicketsAmountByMovieIdUseCase.execute(movieId)
        ticketsInfo?.let {
            _movieName.value = it.name
        } ?: run {
            _movieName.value = "Not found"
        }
    }
}
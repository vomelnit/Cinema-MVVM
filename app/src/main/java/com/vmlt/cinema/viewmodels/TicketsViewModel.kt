package com.vmlt.cinema.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmlt.cinema.utils.TicketsUtils

class TicketsViewModel(private val ticketsModel: TicketsUtils = TicketsUtils) : ViewModel() {
    private val _ticketsAmount = MutableLiveData<Int>()
    val ticketsAmount: LiveData<Int> = _ticketsAmount

    fun buyTicketFromCinema(movieId: Int) {
        ticketsModel.removeTicketFromMovie(movieId)
        updateTicketsAmount(movieId)
    }

    fun returnTicketToCinema(movieId: Int) {
        ticketsModel.addTicketFromMovie(movieId)
        updateTicketsAmount(movieId)
    }

    fun updateTicketsAmount(movieId: Int) {
        _ticketsAmount.value = ticketsModel.getTicketsQuantityForMovie(movieId)
    }
}
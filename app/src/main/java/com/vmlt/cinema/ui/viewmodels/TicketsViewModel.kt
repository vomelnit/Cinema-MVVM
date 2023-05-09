package com.vmlt.cinema.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmlt.cinema.utils.TicketsUtils

class TicketsViewModel(private val ticketsModel: TicketsUtils = TicketsUtils) : ViewModel() {
    private val ticketsAmount = MutableLiveData<Int>()

    fun getTicketsAmount(movieId: Int): LiveData<Int> {
        updateTicketsAmount(movieId)
        return ticketsAmount
    }

    fun buyTicketFromCinema(movieId: Int) {
        ticketsModel.removeTicketFromMovie(movieId)
        updateTicketsAmount(movieId)
    }

    fun returnTicketToCinema(movieId: Int) {
        ticketsModel.addTicketFromMovie(movieId)
    }

    private fun updateTicketsAmount(movieId: Int) {
        ticketsAmount.value = ticketsModel.getTicketsQuantityForMovie(movieId)
    }
}
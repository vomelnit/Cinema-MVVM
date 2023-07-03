package com.vmlt.cinema.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmlt.cinema.domain.entities.TicketsInfo
import com.vmlt.cinema.domain.usecases.BuyTicketByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.GetAvailableTicketsAmountByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.ReturnTicketByMovieIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TicketsViewModel @Inject constructor(
    private val getAvailableTicketsAmountByMovieIdUseCase: GetAvailableTicketsAmountByMovieIdUseCase,
    private val buyTicketByMovieIdUseCase: BuyTicketByMovieIdUseCase,
    private val returnTicketByMovieIdUseCase: ReturnTicketByMovieIdUseCase
) : ViewModel() {
    private val _ticketsAmount = MutableLiveData<Int>()
    val ticketsAmount: LiveData<Int> = _ticketsAmount

    private val _movieName = MutableLiveData<String>()
    val movieName: LiveData<String> = _movieName

    fun buyTicketFromCinema(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            buyTicketByMovieIdUseCase.execute(movieId)
            updateTicketsAmount(movieId)
        }
    }

    fun returnTicketToCinema(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            returnTicketByMovieIdUseCase.execute(movieId)
            updateTicketsAmount(movieId)
        }
    }

    private suspend fun updateTicketsAmount(movieId: Int) = withContext(Dispatchers.IO) {
        val ticketsInfo: TicketsInfo? =
            getAvailableTicketsAmountByMovieIdUseCase.execute(movieId)

        ticketsInfo?.ticketsAmount?.let { ticketsAmount ->
            setTicketValues(ticketsAmount)
        } ?: run {
            setTicketsValuesToErrorState()
        }
    }

    private suspend fun setTicketValues(ticketsAmount: Int) = withContext(Dispatchers.Main) {
        _ticketsAmount.value = ticketsAmount
    }

    private suspend fun setTicketsValuesToErrorState() = withContext(Dispatchers.Main) {
        _ticketsAmount.value = -1
        _movieName.value = "Not found"
    }

    fun refreshScreenInfo(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTicketsAmount(movieId)
            updateMovieName(movieId)
        }
    }

    private suspend fun updateMovieName(movieId: Int) {
        val ticketsInfo: TicketsInfo? =
            getAvailableTicketsAmountByMovieIdUseCase.execute(movieId)
        ticketsInfo?.name?.let { movieName ->
            setMovieName(movieName)
        } ?: run {
            setMovieNameToError()
        }
    }

    private suspend fun setMovieName(movieName: String) = withContext(Dispatchers.Main) {
        _movieName.value = movieName
    }

    private suspend fun setMovieNameToError() = withContext(Dispatchers.Main) {
        _movieName.value = "Not found"
    }
}
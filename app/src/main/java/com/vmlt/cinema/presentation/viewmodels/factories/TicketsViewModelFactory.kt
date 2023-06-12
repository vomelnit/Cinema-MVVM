package com.vmlt.cinema.presentation.viewmodels.factories

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vmlt.cinema.domain.usecases.BuyTicketByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.GetAvailableTicketsAmountByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.ReturnTicketByMovieIdUseCase
import com.vmlt.cinema.presentation.viewmodels.TicketsViewModel

@Suppress("UNCHECKED_CAST")
class TicketsViewModelFactory constructor(
    private var getTicketsAmountUseCase: GetAvailableTicketsAmountByMovieIdUseCase,
    private var buyTicketUseCase: BuyTicketByMovieIdUseCase,
    private var returnTicketUseCase: ReturnTicketByMovieIdUseCase,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(TicketsViewModel::class.java) ->
                TicketsViewModel(getTicketsAmountUseCase, buyTicketUseCase, returnTicketUseCase)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
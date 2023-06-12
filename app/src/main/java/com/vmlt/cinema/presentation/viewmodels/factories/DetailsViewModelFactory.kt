package com.vmlt.cinema.presentation.viewmodels.factories

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vmlt.cinema.domain.usecases.GetMovieDetailsByIdUseCase
import com.vmlt.cinema.presentation.viewmodels.DetailsViewModel

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory constructor(
    private val useCase: GetMovieDetailsByIdUseCase,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(DetailsViewModel::class.java) ->
                DetailsViewModel(useCase)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
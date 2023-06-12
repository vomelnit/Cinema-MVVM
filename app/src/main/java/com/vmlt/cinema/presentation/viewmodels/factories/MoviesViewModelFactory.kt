package com.vmlt.cinema.presentation.viewmodels.factories

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.vmlt.cinema.domain.usecases.GetEntireMovieBasicInfoListUseCase
import com.vmlt.cinema.presentation.viewmodels.MoviesViewModel

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory constructor(
    private val useCase: GetEntireMovieBasicInfoListUseCase,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(MoviesViewModel::class.java) ->
                MoviesViewModel(useCase)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
package com.vmlt.cinema.presentation.di.subcomponent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vmlt.cinema.presentation.viewmodels.DetailsViewModel

import com.vmlt.cinema.presentation.viewmodels.MoviesViewModel
import com.vmlt.cinema.presentation.viewmodels.TicketsViewModel
import com.vmlt.cinema.presentation.viewmodels.factories.CustomViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindCustomViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TicketsViewModel::class)
    abstract fun bindTicketsViewModel(viewModel: TicketsViewModel): ViewModel
}
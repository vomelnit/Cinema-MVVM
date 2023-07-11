package com.vmlt.cinema.presentation.di.subcomponent

import com.vmlt.cinema.presentation.di.subcomponent.viewmodel.ViewModelsModule
import com.vmlt.cinema.presentation.view.details.MovieDetailsFragment
import com.vmlt.cinema.presentation.view.main.MainFragment
import com.vmlt.cinema.presentation.view.tickets.TicketsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [ViewModelsModule::class]
)
interface MovieComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)
    fun inject(ticketsFragment: TicketsFragment)

}

package com.vmlt.cinema.presentation.di

import com.vmlt.cinema.presentation.di.subcomponent.MovieComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        CinemaDatabaseModule::class,
        MovieRepositoryModule::class,
        UseCasesModule::class,
        AppSubcomponentsModule::class
    ]
)
interface AppComponent {

    fun movieListComponent(): MovieComponent.Factory
}

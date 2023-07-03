package com.vmlt.cinema.presentation.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, CinemaDatabaseModule::class, MovieRepositoryModule::class, AppSubcomponentsModule::class]
)
interface AppComponent {

    fun movieListComponent(): MovieComponent.Factory
}

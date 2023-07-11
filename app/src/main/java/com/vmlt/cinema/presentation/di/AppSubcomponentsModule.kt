package com.vmlt.cinema.presentation.di

import com.vmlt.cinema.presentation.di.subcomponent.MovieComponent
import dagger.Module

@Module(subcomponents = [MovieComponent::class])
class AppSubcomponentsModule

package com.vmlt.cinema.presentation.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    fun providesApp() = application
}
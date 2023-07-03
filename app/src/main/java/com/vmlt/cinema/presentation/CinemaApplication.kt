package com.vmlt.cinema.presentation

import android.app.Application
import com.vmlt.cinema.presentation.di.AppComponent
import com.vmlt.cinema.presentation.di.ApplicationModule
import com.vmlt.cinema.presentation.di.DaggerAppComponent


open class CinemaApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}


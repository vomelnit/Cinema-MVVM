package com.vmlt.cinema.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vmlt.cinema.R
import com.vmlt.cinema.presentation.CinemaApplication
import com.vmlt.cinema.presentation.di.subcomponent.MovieComponent

class MainActivity : AppCompatActivity() {
    lateinit var movieListComponent: MovieComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        movieListComponent =
            (applicationContext as CinemaApplication).appComponent.movieListComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
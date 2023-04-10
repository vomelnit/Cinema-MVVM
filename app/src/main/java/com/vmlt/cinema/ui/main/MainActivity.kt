package com.vmlt.cinema.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmlt.cinema.utils.model.MovieBasicInfo
import com.vmlt.cinema.utils.MovieUtils
import com.vmlt.cinema.ui.main.MainContract.MainView
import com.vmlt.cinema.R

class MainActivity : ComponentActivity(), MainView {
    private lateinit var mainPresenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter = MainPresenter(this, MovieUtils, )
        setContentView(R.layout.activity_main)

        updateMovieList()
    }

    override fun updateMovieList() {
        val recyclerview = findViewById<RecyclerView>(R.id.movie_recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<MovieBasicInfo>()
        for (movie in mainPresenter.getEntireMovieList()) {
            data.add(movie)
        }

        val adapter = MovieBasicInfoAdapter(data)
        recyclerview.adapter = adapter
    }
}
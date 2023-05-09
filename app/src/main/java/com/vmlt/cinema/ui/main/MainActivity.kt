package com.vmlt.cinema.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmlt.cinema.R
import com.vmlt.cinema.ui.viewmodels.MoviesViewModel
import com.vmlt.cinema.utils.model.MovieBasicInfo

class MainActivity : ComponentActivity() {
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        setContentView(R.layout.activity_main)

        updateMovieList()
    }

    private fun updateMovieList() {
        val recyclerview = findViewById<RecyclerView>(R.id.movie_recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<MovieBasicInfo>()
        moviesViewModel.getAvailableMovieList().observe(this, Observer { movieList ->
            for (movie in movieList) {
                data.add(movie)
            }
        })

        val adapter = MovieBasicInfoAdapter(data)
        recyclerview.adapter = adapter
    }
}
package com.vmlt.cinema.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmlt.cinema.R
import com.vmlt.cinema.view.details.INTENT_EXTRA_MOVIE_ID_KEY
import com.vmlt.cinema.view.details.MovieDetailsActivity
import com.vmlt.cinema.viewmodels.MoviesViewModel

class MainActivity : ComponentActivity() {
    private lateinit var adapter: MovieBasicInfoAdapter
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUi()
        setupViewModel()
    }

    private fun setupUi() {
        adapter = MovieBasicInfoAdapter(::onItemClick)

        val recyclerView = findViewById<RecyclerView>(R.id.movie_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        moviesViewModel.movieBasicInfoList.observe(this, Observer {
            adapter.updateList(it)
        })

        moviesViewModel.getAvailableMovieList()
    }

    private fun onItemClick(id: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(INTENT_EXTRA_MOVIE_ID_KEY, id)
        startActivity(intent)
    }
}
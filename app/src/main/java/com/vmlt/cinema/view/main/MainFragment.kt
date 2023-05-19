package com.vmlt.cinema.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vmlt.cinema.R
import com.vmlt.cinema.view.details.INTENT_EXTRA_MOVIE_ID_KEY
import com.vmlt.cinema.viewmodels.MoviesViewModel

class MainFragment : Fragment() {

    private lateinit var adapter: MovieBasicInfoAdapter
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_main,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUi()
        setupViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupUi() {
        adapter = MovieBasicInfoAdapter(::onItemClick)

        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.movie_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }

    private fun setupViewModel() {
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        moviesViewModel.movieBasicInfoList.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        moviesViewModel.getAvailableMovieList()
    }

    private fun onItemClick(id: Int) {
        val navBundle = Bundle()
        navBundle.putInt(INTENT_EXTRA_MOVIE_ID_KEY, id)
        findNavController().navigate(R.id.action_mainFragment_to_movieDetailsFragment, navBundle)
    }
}
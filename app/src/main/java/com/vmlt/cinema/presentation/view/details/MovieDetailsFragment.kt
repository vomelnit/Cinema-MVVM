package com.vmlt.cinema.presentation.view.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vmlt.cinema.R
import com.vmlt.cinema.domain.entities.Movie
import com.vmlt.cinema.presentation.view.MainActivity
import com.vmlt.cinema.presentation.viewmodels.DetailsViewModel
import com.vmlt.cinema.presentation.viewmodels.factories.CustomViewModelFactory
import javax.inject.Inject

const val INTENT_EXTRA_MOVIE_ID_KEY = "MovieId"

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    lateinit var detailsViewModel: DetailsViewModel

    private var movieId: Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).movieListComponent.inject(this)
        detailsViewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_movie_details,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieId = arguments?.getInt(INTENT_EXTRA_MOVIE_ID_KEY) ?: -1

        setupViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViewModel() {
        detailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer { movie ->
            view?.let { setMovieDetailsContent(movie, it) }
        })

        detailsViewModel.getMovieDetails(movieId)
    }

    private fun setMovieDetailsContent(movieDetails: Movie?, rootView: View) {

        val movie = movieDetails ?: return

        val moviePosterImage: ImageView = rootView.findViewById(R.id.movie_poster_image)
        val movieRatingText: TextView = rootView.findViewById(R.id.movie_rating_text)
        val movieNameText: TextView = rootView.findViewById(R.id.movie_name_text)
        val movieYearText: TextView = rootView.findViewById(R.id.movie_year_text)
        val movieGenreText: TextView = rootView.findViewById(R.id.movie_genre_text)
        val buyTicketBtn: Button = rootView.findViewById(R.id.buy_ticket_btn)

        moviePosterImage.setImageResource(movie.iconId)
        movieNameText.text = movie.name
        movieRatingText.text = movie.rating.toString()
        movieYearText.text = movie.year.toString()
        movieGenreText.text = movie.genre

        buyTicketBtn.setOnClickListener {
            startFragmentForTickets(movieId)
        }
    }

    private fun startFragmentForTickets(movieId: Int) {
        val navBundle: Bundle? = Bundle()
        navBundle?.putInt(INTENT_EXTRA_MOVIE_ID_KEY, movieId)
        findNavController().navigate(R.id.action_movieDetailsFragment_to_ticketsFragment, navBundle)
    }
}
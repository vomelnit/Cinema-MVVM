package com.vmlt.cinema.utils

import com.vmlt.cinema.R
import com.vmlt.cinema.model.Movie
import com.vmlt.cinema.model.MovieBasicInfo

object MovieUtils {
    private val movieList: List<Movie> = generateMoviesList()

    fun getAllAvailableMoviesBasicInfo(): List<MovieBasicInfo> {
        val movieBasicInfoList: MutableList<MovieBasicInfo> = mutableListOf()

        movieList.forEach { movie ->
            movieBasicInfoList.add(
                MovieBasicInfo(
                    movie.id,
                    movie.name,
                    movie.iconId
                )
            )
        }

        return movieBasicInfoList
    }

    fun getMovieInfoById(movieId: Int): Movie? {
        return movieList.firstOrNull { it.id == movieId }
    }

    fun getMovieNameById(movieId: Int): String? {
        return movieList.firstOrNull { it.id == movieId }?.name
    }

    /* Mock function */
    private fun generateMoviesList(): List<Movie> {
        return listOf(
            Movie(1, "The Strays", 6.5f, R.drawable.the_strays_poster, 2023, "Thriller, Drama"),
            Movie(2, "The Whale", 7.7f, R.drawable.the_whale_poster, 2022, "Drama"),
            Movie(3, "All that breathes", 6.5f, R.drawable.all_that_breathes_poster, 2010, "Horror"),
            Movie(4, "We have a ghost", 3.3f, R.drawable.we_have_a_ghost_poster, 2018, "Comedy"),
        )
    }
}
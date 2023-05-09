package com.vmlt.cinema.model

data class Movie(
    val id: Int,
    val name: String,
    val rating: Float,
    val iconId: Int,
    val year: Int,
    val genre: String
)
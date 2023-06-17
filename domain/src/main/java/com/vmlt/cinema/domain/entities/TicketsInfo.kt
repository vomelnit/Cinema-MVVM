package com.vmlt.cinema.domain.entities

data class TicketsInfo(
    val id: Int,
    val name: String?,
    val ticketsAmount: Int?,
    val maxTicketsAmount: Int?
)

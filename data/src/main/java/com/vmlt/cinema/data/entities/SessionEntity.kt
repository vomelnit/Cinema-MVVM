package com.vmlt.cinema.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sessions",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movieId"),
            onDelete = ForeignKey.CASCADE
        )
    ),
    indices = [Index(value = ["movieId"], unique = true)]
)
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movieId: Int,
    val availableTicketsAmount: Int,
    val maxTicketsAmount: Int,
    val price: Double,
    val hallNumber: Int
)
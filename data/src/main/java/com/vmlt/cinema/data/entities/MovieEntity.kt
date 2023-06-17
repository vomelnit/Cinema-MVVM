package com.vmlt.cinema.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "movies",
    indices = [Index(value = ["name"], unique = true)]
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int,
    val name: String,
    val rating: Float?,
    val iconId: Int,
    val year: Int,
    val genre: String
)
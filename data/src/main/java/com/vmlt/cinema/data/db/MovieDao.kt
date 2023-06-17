package com.vmlt.cinema.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vmlt.cinema.data.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MovieEntity)

    @Update
    fun update(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT name FROM movies WHERE id = :id")
    fun getMovieNameById(id: Int): String?

    @Query("SELECT * FROM movies ORDER BY name ASC")
    fun getAllMovies(): List<MovieEntity>
}
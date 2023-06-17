package com.vmlt.cinema.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vmlt.cinema.data.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(session: SessionEntity)

    @Update
    fun update(session: SessionEntity)

    @Delete
    fun delete(session: SessionEntity)

    @Query("SELECT * from sessions WHERE id = :id")
    fun getSessionById(id: Int): SessionEntity?

    @Query("SELECT * from sessions WHERE movieId = :movieId")
    fun getSessionByMovieId(movieId: Int): SessionEntity?

    @Query("SELECT availableTicketsAmount from sessions WHERE movieId = :movieId")
    fun getTicketAmountByMovieId(movieId: Int): Int?

    @Query("SELECT maxTicketsAmount from sessions WHERE movieId = :movieId")
    fun getMaxTicketAmountByMovieId(movieId: Int): Int?

    @Query("SELECT * from sessions WHERE id = :id")
    fun getTicketInfoById(id: Int): SessionEntity?

    @Query("UPDATE sessions SET availableTicketsAmount = :availableTicketsAmount WHERE movieId = :movieId")
    fun updateAvailableTicketAmountByMovieId(movieId: Int, availableTicketsAmount: Int)

}
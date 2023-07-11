package com.vmlt.cinema.presentation.di

import com.vmlt.cinema.domain.repositories.MovieRepository
import com.vmlt.cinema.domain.usecases.BuyTicketByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.GetAvailableTicketsAmountByMovieIdUseCase
import com.vmlt.cinema.domain.usecases.GetEntireMovieBasicInfoListUseCase
import com.vmlt.cinema.domain.usecases.GetMovieDetailsByIdUseCase
import com.vmlt.cinema.domain.usecases.ReturnTicketByMovieIdUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun providesBuyTicketByMovieIdUseCase(repository: MovieRepository) =
        BuyTicketByMovieIdUseCase(repository)

    @Provides
    fun providesGetAvailableTicketsAmountByMovieIdUseCase(repository: MovieRepository) =
        GetAvailableTicketsAmountByMovieIdUseCase(repository)

    @Provides
    fun providesGetEntireMovieBasicInfoListUseCase(repository: MovieRepository) =
        GetEntireMovieBasicInfoListUseCase(repository)

    @Provides
    fun providesGetMovieDetailsByIdUseCase(repository: MovieRepository) =
        GetMovieDetailsByIdUseCase(repository)

    @Provides
    fun providesReturnTicketByMovieIdUseCase(repository: MovieRepository) =
        ReturnTicketByMovieIdUseCase(repository)
}

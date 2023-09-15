package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.CheckFavouriteResponse
import io.reactivex.Single

class CheckFavoriteMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(id: Int): Single<CheckFavouriteResponse> {
        return moviesRemoteRepository.checkFavourite(id.toString())
    }

}
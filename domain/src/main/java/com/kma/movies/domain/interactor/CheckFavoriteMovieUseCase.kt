package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.CheckFavouriteResponse
import io.reactivex.Single

class CheckFavoriteMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(id: Int): Single<CheckFavouriteResponse> {
        return moviesRemoteRepository.checkFavourite(id.toString())
    }

}
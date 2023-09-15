package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.FavouriteResponse
import io.reactivex.Single

class AddFavoriteMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(movieId: String): Single<FavouriteResponse> {
        return moviesRemoteRepository.addFavourite(movieId)
    }

}
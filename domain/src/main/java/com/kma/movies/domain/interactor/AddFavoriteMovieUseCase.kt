package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.FavouriteResponse
import io.reactivex.Single

class AddFavoriteMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(movieId: String): Single<FavouriteResponse> {
        return moviesRemoteRepository.addFavourite(movieId)
    }

}
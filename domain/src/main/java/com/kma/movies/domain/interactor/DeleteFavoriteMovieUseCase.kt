package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.DeleteFavouriteResponse
import io.reactivex.Single

class DeleteFavoriteMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(id: String): Single<DeleteFavouriteResponse> {
        return moviesRemoteRepository.deleteFavourite(id)
    }

}
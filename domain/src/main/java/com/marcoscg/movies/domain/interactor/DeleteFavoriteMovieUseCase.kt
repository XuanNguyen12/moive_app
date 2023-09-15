package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesLocalRepository
import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.DeleteFavouriteResponse
import com.marcoscg.movies.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

class DeleteFavoriteMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(id: String): Single<DeleteFavouriteResponse> {
        return moviesRemoteRepository.deleteFavourite(id)
    }

}
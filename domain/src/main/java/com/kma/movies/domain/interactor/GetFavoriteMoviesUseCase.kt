package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.Movie
import io.reactivex.Single

class GetFavoriteMoviesUseCase(private val moviesLocalRepository: MoviesRemoteRepository) {

    fun execute(): Single<List<Movie>> {
        return moviesLocalRepository.getFavourite()
    }

}
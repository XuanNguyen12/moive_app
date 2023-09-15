package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesLocalRepository
import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.Movie
import com.marcoscg.movies.model.MoviesResponse
import io.reactivex.Observable
import io.reactivex.Single

class GetFavoriteMoviesUseCase(private val moviesLocalRepository: MoviesRemoteRepository) {

    fun execute(): Single<List<Movie>> {
        return moviesLocalRepository.getFavourite()
    }

}
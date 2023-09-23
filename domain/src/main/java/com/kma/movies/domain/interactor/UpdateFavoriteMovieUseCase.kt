package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesLocalRepository
import com.kma.movies.model.Movie
import io.reactivex.Completable

class UpdateFavoriteMovieUseCase(private val moviesLocalRepository: MoviesLocalRepository) {

    fun execute(movie: Movie): Completable {
        return moviesLocalRepository.updateFavoriteMovie(movie)
    }

}
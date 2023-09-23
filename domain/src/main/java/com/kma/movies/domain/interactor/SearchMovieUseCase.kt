package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.MoviesResponse
import com.kma.movies.model.SearchMovies
import io.reactivex.Single

class SearchMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(page: Int, searchMovies : SearchMovies): Single<MoviesResponse> {
        return moviesRemoteRepository.searchMovie(page, searchMovies)
    }

}
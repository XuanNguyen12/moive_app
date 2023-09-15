package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesLocalRepository
import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.CheckFavouriteResponse
import com.marcoscg.movies.model.Movie
import com.marcoscg.movies.model.MoviesResponse
import com.marcoscg.movies.model.SearchMovies
import io.reactivex.Single

class SearchMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {

    fun execute(page: Int, searchMovies : SearchMovies): Single<MoviesResponse> {
        return moviesRemoteRepository.searchMovie(page, searchMovies)
    }

}
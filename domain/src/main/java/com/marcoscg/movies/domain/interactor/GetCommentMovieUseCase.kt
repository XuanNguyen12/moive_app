package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.GetCommentResponse
import com.marcoscg.movies.model.MovieDetail
import io.reactivex.Single

class GetCommentMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute(id: String): Single<List<GetCommentResponse>> {
        return moviesRemoteRepository.getCommentMovie(id)
    }
}
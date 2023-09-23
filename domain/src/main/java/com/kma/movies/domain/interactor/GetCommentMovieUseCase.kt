package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.GetCommentResponse
import io.reactivex.Single

class GetCommentMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute(id: String): Single<List<GetCommentResponse>> {
        return moviesRemoteRepository.getCommentMovie(id)
    }
}
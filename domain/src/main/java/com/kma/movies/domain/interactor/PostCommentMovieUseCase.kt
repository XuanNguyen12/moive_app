package com.kma.movies.domain.interactor

import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.model.PostComment
import com.kma.movies.model.PostCommentResponse
import io.reactivex.Single

class PostCommentMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute(postComment: PostComment): Single<PostCommentResponse> {
        return moviesRemoteRepository.postCommentMovie(postComment)
    }
}
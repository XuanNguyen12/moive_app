package com.marcoscg.movies.domain.interactor

import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.model.GetCommentResponse
import com.marcoscg.movies.model.MovieDetail
import com.marcoscg.movies.model.PostComment
import com.marcoscg.movies.model.PostCommentResponse
import io.reactivex.Single

class PostCommentMovieUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {
    fun execute(postComment: PostComment): Single<PostCommentResponse> {
        return moviesRemoteRepository.postCommentMovie(postComment)
    }
}
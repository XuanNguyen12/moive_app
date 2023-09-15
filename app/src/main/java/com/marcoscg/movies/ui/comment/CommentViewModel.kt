package com.marcoscg.movies.ui.comment

import androidx.lifecycle.ViewModel
import com.marcoscg.movies.data.Resource
import com.marcoscg.movies.domain.interactor.GetCommentMovieUseCase
import com.marcoscg.movies.domain.interactor.PostCommentMovieUseCase
import com.marcoscg.movies.model.GetCommentResponse
import com.marcoscg.movies.model.PostComment
import com.marcoscg.movies.model.PostCommentResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CommentViewModel(private val getCommentMovieUseCase: GetCommentMovieUseCase,
                       private val postCommentMovieUseCase: PostCommentMovieUseCase
    ) : ViewModel() {

    private val stateFlow = MutableStateFlow<Resource<List<GetCommentResponse>>>(Resource.empty())
    private val commentStateFlow = MutableStateFlow<Resource<PostCommentResponse>>(Resource.empty())
    private var currentPage = 1
    private var lastPage = 1

    var disposable: Disposable? = null

    val commentMoviesState: StateFlow<Resource<List<GetCommentResponse>>>
        get() = stateFlow
    val postCommentMoviesState: StateFlow<Resource<PostCommentResponse>>
        get() = commentStateFlow

    fun getCommentMovies(movieId : String) {
        stateFlow.value = Resource.loading()

        disposable = getCommentMovieUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                stateFlow.value = Resource.success(res)
            }, { throwable ->
                lastPage = currentPage // prevent loading more pages
                throwable.localizedMessage?.let {
                    stateFlow.value = Resource.error(it)
                }
            })
    }
    fun postComment(postComment: PostComment) {
        commentStateFlow.value = Resource.loading()

        disposable = postCommentMovieUseCase.execute(postComment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                commentStateFlow.value = Resource.success(res)
            }, { throwable ->
                lastPage = currentPage // prevent loading more pages
                throwable.localizedMessage?.let {
                    commentStateFlow.value = Resource.error(it)
                }
            })
    }




}
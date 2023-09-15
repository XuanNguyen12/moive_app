package com.marcoscg.movies.ui.details.viewmodel

import androidx.lifecycle.ViewModel
import com.marcoscg.movies.data.Resource
import com.marcoscg.movies.domain.interactor.*
import com.marcoscg.movies.model.GetCommentResponse
import com.marcoscg.movies.model.MovieDetail
import com.marcoscg.movies.model.toSimple
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieDetailsViewModel(private val getSingleMovieUseCase: GetSingleMovieUseCase,
                            private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
                            private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
                            private val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase,
                            private val getFavoriteMovieUseCase: CheckFavoriteMovieUseCase,
                            private val getCommentMovieUseCase: GetCommentMovieUseCase,
) : ViewModel() {

    private val singleMovieStateFlow = MutableStateFlow<Resource<MovieDetail>>(Resource.empty())
    private val favoritesStateFlow = MutableStateFlow<Resource<Boolean>>(Resource.empty())
    private val commentStateFlow = MutableStateFlow<Resource<List<GetCommentResponse>>>(Resource.empty())
    var disposable: Disposable? = null

    val singleMovieState: StateFlow<Resource<MovieDetail>>
        get() = singleMovieStateFlow

    val favoritesState: StateFlow<Resource<Boolean>>
        get() = favoritesStateFlow
    val commentMoviesState: StateFlow<Resource<List<GetCommentResponse>>>
        get() = commentStateFlow

    fun fetchSingleMovie(id: String) {
        singleMovieStateFlow.value = Resource.loading()

        disposable = getSingleMovieUseCase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                singleMovieStateFlow.value = Resource.success(res)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    singleMovieStateFlow.value = Resource.error(it)
                }
            })
    }

    private fun addFavoriteMovie(movieId: String) {
        favoritesStateFlow.value = Resource.loading()
        disposable = addFavoriteMovieUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favoritesStateFlow.value = Resource.success(true)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    favoritesStateFlow.value = Resource.error(it)
                }
            })
    }

    private fun deleteFavoriteMovie(movie: MovieDetail) {
        favoritesStateFlow.value = Resource.loading()

        disposable = deleteFavoriteMovieUseCase.execute(movie.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favoritesStateFlow.value = Resource.success(false)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    favoritesStateFlow.value = Resource.error(it)
                }
            })
    }

    fun updateFavoriteMovie(movie: MovieDetail) {
        favoritesStateFlow.value = Resource.loading()

        disposable = updateFavoriteMovieUseCase.execute(movie.toSimple())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favoritesStateFlow.value = Resource.success(true)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    favoritesStateFlow.value = Resource.error(it)
                }
            })
    }

    fun toggleFavorite(movie: MovieDetail) {
        favoritesStateFlow.value = Resource.loading()
        disposable = getFavoriteMovieUseCase.execute(movie.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isFavourite == true) {
                    deleteFavoriteMovie(movie)
                } else {
                    addFavoriteMovie(movie.id.toString())
                }
            }, {
                // favorite movie does not exist
            })
    }

    fun fetchFavoriteMovieState(movie: MovieDetail) {
        favoritesStateFlow.value = Resource.loading()

        disposable = getFavoriteMovieUseCase.execute(movie.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // favorite movie exists, update data first
                favoritesStateFlow.value = Resource.success(it.isFavourite)
            }, {
                // favorite movie does not exist
                it.localizedMessage?.let {
                    favoritesStateFlow.value = Resource.error(it)
                }
            })
    }

    fun getCommentMovies(movieId : String) {
        commentStateFlow.value = Resource.loading()
        disposable = getCommentMovieUseCase.execute(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                commentStateFlow.value = Resource.success(res)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    commentStateFlow.value = Resource.error(it)
                }
            })
    }
}
package com.marcoscg.movies.ui.search

import androidx.lifecycle.ViewModel
import com.marcoscg.movies.data.Resource
import com.marcoscg.movies.domain.interactor.SearchMovieUseCase
import com.marcoscg.movies.model.Movie
import com.marcoscg.movies.model.SearchMovies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel(
                      private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val stateFlow = MutableStateFlow<Resource<List<Movie>>>(Resource.empty())
    private var currentPage = 1
    private var lastPage = 1
    private var currentQuery = ""
    var disposable: Disposable? = null

    val searchMoviesState: StateFlow<Resource<List<Movie>>>
        get() = stateFlow

    fun fetchPopularMovies(query : String) {
        currentQuery = query
        stateFlow.value = Resource.loading()
        disposable = searchMovieUseCase.execute(currentPage, SearchMovies(currentQuery))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                lastPage = res.total_pages
                stateFlow.value = Resource.success(res.results)
            }, { throwable ->
                lastPage = currentPage // prevent loading more pages
                throwable.localizedMessage?.let {
                    stateFlow.value = Resource.error(it)
                }
            })
    }
    fun fetchNextPopularMovies() {
        currentPage++
        fetchPopularMovies(currentQuery)
    }

    fun refreshPopularMovies() {
        currentPage = 1
        fetchPopularMovies(currentQuery)
    }

    fun isFirstPage(): Boolean {
        return currentPage == 1
    }

    fun isLastPage(): Boolean {
        return currentPage == lastPage
    }

}
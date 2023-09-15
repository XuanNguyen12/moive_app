package com.marcoscg.movies.di

import com.marcoscg.movies.data.repository.MoviesLocalRepositoryImpl
import com.marcoscg.movies.data.repository.MoviesRemoteRepositoryImpl
import com.marcoscg.movies.data.sources.local.mapper.MoviesLocalMapper
import com.marcoscg.movies.data.sources.remote.mapper.MoviesRemoteMapper
import com.marcoscg.movies.domain.interactor.*
import com.marcoscg.movies.domain.repository.MoviesLocalRepository
import com.marcoscg.movies.domain.repository.MoviesRemoteRepository
import com.marcoscg.movies.ui.comment.CommentViewModel
import com.marcoscg.movies.ui.details.viewmodel.CommentListAdapter
import com.marcoscg.movies.ui.search.SearchViewModel
import com.marcoscg.movies.ui.details.viewmodel.MovieDetailsViewModel
import com.marcoscg.movies.ui.home.master.MovieListAdapter
import com.marcoscg.movies.ui.home.viewmodel.FavoriteViewModel
import com.marcoscg.movies.ui.home.viewmodel.PopularViewModel
import com.marcoscg.movies.ui.home.viewmodel.ProfileViewModel
import com.marcoscg.movies.ui.home.viewmodel.UpcomingViewModel
import com.marcoscg.movies.ui.sign_in.viewmodel.LoginViewModel
import com.marcoscg.movies.ui.sign_in.viewmodel.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { MoviesRemoteMapper() }
    single { MoviesLocalMapper() }
    factory<MoviesRemoteRepository> { MoviesRemoteRepositoryImpl(get()) }
    factory<MoviesLocalRepository> { MoviesLocalRepositoryImpl(androidContext(), get()) }
    factory { MovieListAdapter(androidContext()) }
    factory { CommentListAdapter(androidContext()) }
}

val popularMoviesModule = module {
    factory { GetPopularMoviesUseCase(get()) }
    viewModel { PopularViewModel(get()) }
}

val upcomingMoviesModule = module {
    factory { GetUpcomingMoviesUseCase(get()) }
    viewModel { UpcomingViewModel(get()) }
}

val favoriteMoviesModule = module {
    factory { GetFavoriteMoviesUseCase(get()) }
    viewModel { FavoriteViewModel(get()) }
}
val registerMoviesModule = module {
    factory { UserRegisterUseCase(get()) }
    viewModel { SignUpViewModel(get()) }
}
val signUpMoviesModule = module {
    factory { UserLoginUseCase(get()) }
    viewModel { LoginViewModel(get()) }
}
val profileModule = module {
    factory { ProfileUseCase(get()) }
    viewModel { ProfileViewModel(get()) }
}
val searchModule = module {
    factory { SearchMovieUseCase(get()) }
    viewModel { SearchViewModel(get()) }
}
val getCommentModule = module {
    factory { GetCommentMovieUseCase(get()) }
    factory { PostCommentMovieUseCase(get()) }
    viewModel { CommentViewModel(get(), get()) }
}

val movieDetailsModule = module {
    factory { GetSingleMovieUseCase(get()) }
    factory { AddFavoriteMovieUseCase(get()) }
    factory { DeleteFavoriteMovieUseCase(get()) }
    factory { UpdateFavoriteMovieUseCase(get()) }
    factory { CheckFavoriteMovieUseCase(get()) }
    factory { GetCommentMovieUseCase(get()) }
    viewModel { MovieDetailsViewModel(get(), get(), get(), get(), get(), get()) }
}
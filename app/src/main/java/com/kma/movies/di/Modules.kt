package com.kma.movies.di

import com.kma.movies.data.repository.MoviesLocalRepositoryImpl
import com.kma.movies.data.repository.MoviesRemoteRepositoryImpl
import com.kma.movies.data.sources.local.mapper.MoviesLocalMapper
import com.kma.movies.data.sources.remote.mapper.MoviesRemoteMapper
import com.kma.movies.domain.interactor.*
import com.kma.movies.domain.repository.MoviesLocalRepository
import com.kma.movies.domain.repository.MoviesRemoteRepository
import com.kma.movies.ui.comment.CommentViewModel
import com.kma.movies.ui.details.viewmodel.CommentListAdapter
import com.kma.movies.ui.search.SearchViewModel
import com.kma.movies.ui.details.viewmodel.MovieDetailsViewModel
import com.kma.movies.ui.home.master.MovieListAdapter
import com.kma.movies.ui.home.viewmodel.FavoriteViewModel
import com.kma.movies.ui.home.viewmodel.PopularViewModel
import com.kma.movies.ui.home.viewmodel.ProfileViewModel
import com.kma.movies.ui.home.viewmodel.UpcomingViewModel
import com.kma.movies.ui.sign_in.viewmodel.LoginViewModel
import com.kma.movies.ui.sign_in.viewmodel.SignUpViewModel
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
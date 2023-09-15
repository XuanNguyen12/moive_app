package com.marcoscg.movies.domain.repository

import com.marcoscg.movies.model.DataUserResponse
import com.marcoscg.movies.model.FavouriteResponse
import com.marcoscg.movies.model.LoginResponse
import com.marcoscg.movies.model.Movie
import com.marcoscg.movies.model.MovieDetail
import com.marcoscg.movies.model.MoviesResponse
import com.marcoscg.movies.model.RegisterStatus
import com.marcoscg.movies.model.UserLogin
import com.marcoscg.movies.model.UserRegister
import io.reactivex.Single

interface MoviesRemoteRepository {

    fun getPopularMovies(page: Int): Single<MoviesResponse>

    fun getUpcomingMovies(page: Int): Single<MoviesResponse>

    fun getSingleMovie(id: String): Single<MovieDetail>
    fun registerUser(userRegister: UserRegister): Single<RegisterStatus>
    fun loginUser(userLogin: UserLogin): Single<LoginResponse>
    fun getDataUser() : Single<DataUserResponse>
    fun setFavourite(id : String) : Single<FavouriteResponse>
    fun getFavourite() : Single<List<Movie>>

}
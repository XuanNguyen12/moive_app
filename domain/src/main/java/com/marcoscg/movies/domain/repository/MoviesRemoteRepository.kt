package com.marcoscg.movies.domain.repository

import com.marcoscg.movies.model.CheckFavouriteResponse
import com.marcoscg.movies.model.DataUserResponse
import com.marcoscg.movies.model.DeleteFavouriteResponse
import com.marcoscg.movies.model.FavouriteResponse
import com.marcoscg.movies.model.GetCommentResponse
import com.marcoscg.movies.model.LoginResponse
import com.marcoscg.movies.model.Movie
import com.marcoscg.movies.model.MovieDetail
import com.marcoscg.movies.model.MoviesResponse
import com.marcoscg.movies.model.PostComment
import com.marcoscg.movies.model.PostCommentResponse
import com.marcoscg.movies.model.RegisterStatus
import com.marcoscg.movies.model.SearchMovies
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
    fun addFavourite(id : String) : Single<FavouriteResponse>
    fun deleteFavourite(id : String) : Single<DeleteFavouriteResponse>
    fun getFavourite() : Single<List<Movie>>
    fun checkFavourite(id : String) : Single<CheckFavouriteResponse>
    fun searchMovie(page: Int, searchMovies : SearchMovies): Single<MoviesResponse>
    fun getCommentMovie(id : String): Single<List<GetCommentResponse>>
    fun postCommentMovie(postComment : PostComment): Single<PostCommentResponse>

}
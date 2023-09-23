package com.kma.movies.domain.repository

import com.kma.movies.model.CheckFavouriteResponse
import com.kma.movies.model.DataUserResponse
import com.kma.movies.model.DeleteFavouriteResponse
import com.kma.movies.model.FavouriteResponse
import com.kma.movies.model.GetCommentResponse
import com.kma.movies.model.LoginResponse
import com.kma.movies.model.Movie
import com.kma.movies.model.MovieDetail
import com.kma.movies.model.MoviesResponse
import com.kma.movies.model.PostComment
import com.kma.movies.model.PostCommentResponse
import com.kma.movies.model.RegisterStatus
import com.kma.movies.model.SearchMovies
import com.kma.movies.model.UserLogin
import com.kma.movies.model.UserRegister
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
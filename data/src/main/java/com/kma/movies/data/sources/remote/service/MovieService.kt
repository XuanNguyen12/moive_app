package com.kma.movies.data.sources.remote.service

import com.kma.movies.data.sources.remote.model.MovieIdDto
import com.kma.movies.data.sources.remote.model.RemoteCheckFavouriteResponse
import com.kma.movies.data.sources.remote.model.RemoteFavouriteResponse
import com.kma.movies.data.sources.remote.model.RemoteDataUserResponse
import com.kma.movies.data.sources.remote.model.RemoteDeleteFavouriteResponse
import com.kma.movies.data.sources.remote.model.RemoteGetCommentResponse
import com.kma.movies.data.sources.remote.model.RemoteLoginResponse
import com.kma.movies.data.sources.remote.model.RemoteMovie
import com.kma.movies.data.sources.remote.model.RemoteMovieDetail
import com.kma.movies.data.sources.remote.model.RemoteMoviesResponse
import com.kma.movies.data.sources.remote.model.RemotePostComment
import com.kma.movies.data.sources.remote.model.RemotePostCommentResponse
import com.kma.movies.data.sources.remote.model.RemoteRegisterResponse
import com.kma.movies.data.sources.remote.model.RemoteUserLogin
import com.kma.movies.data.sources.remote.model.RemoteUserRegister
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movies/movies")
    fun getPopularMovies(@Query("page") page: Int): Single<RemoteMoviesResponse>

    @GET("movies/movies")
    fun getUpcomingMovies(@Query("page") page: Int): Single<RemoteMoviesResponse>

    @GET("movies/movies/{id}")
    fun getSingleMovie(@Path("id") id: String): Single<RemoteMovieDetail>


    @POST("users/register")
    fun registerUser(
        @Body remoteUserRegister: RemoteUserRegister
    ): Single<RemoteRegisterResponse>


    @POST("users/login")
    fun loginUser(
        @Body remoteUserLogin: RemoteUserLogin
    ): Single<RemoteLoginResponse>

    @GET("users/current")
    fun getDataUser(): Single<RemoteDataUserResponse>

    @POST("movies/favorites")
    fun setFavourite(
        @Body movieIdDto: MovieIdDto
    ): Single<RemoteFavouriteResponse>

    @GET("movies/favorites")
    fun getFavourite(): Single<List<RemoteMovie>>

    @HTTP(method = "DELETE", path = "movies/favorites", hasBody = true)
    fun deleteFavourite(
        @Body movieIdDto: MovieIdDto
    ): Single<RemoteDeleteFavouriteResponse>

    @GET("movies/favorites/check/{id}")
    fun checkFavourite(
        @Path("id") id: String
    ): Single<RemoteCheckFavouriteResponse>

    @GET("movies/search")
    fun searchMovie(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): Single<RemoteMoviesResponse>

    @GET("movies/comments/{id}")
    fun getCommentMovie(@Path("id") id: String): Single<List<RemoteGetCommentResponse>>
    @POST("movies/comments")
    fun postCommentMovie(@Body remotePostComment: RemotePostComment): Single<RemotePostCommentResponse>

}
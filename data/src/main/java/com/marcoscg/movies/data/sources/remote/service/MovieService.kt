package com.marcoscg.movies.data.sources.remote.service

import com.marcoscg.movies.data.sources.remote.model.RemoteDataUserResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteLoginResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteMovieDetail
import com.marcoscg.movies.data.sources.remote.model.RemoteMoviesResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteRegisterResponse
import com.marcoscg.movies.data.sources.remote.model.RemoteUserLogin
import com.marcoscg.movies.data.sources.remote.model.RemoteUserRegister
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Header as Header

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
    fun getDataUser(
        @Header("Authorization") authHeader: String
    ): Single<RemoteDataUserResponse>


}
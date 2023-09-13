package com.marcoscg.movies.data.sources.remote.service

import com.marcoscg.movies.data.sources.remote.model.RemoteMovieDetail
import com.marcoscg.movies.data.sources.remote.model.RemoteMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movies/movies")
    fun getPopularMovies(@Query("page") page: Int): Single<RemoteMoviesResponse>

    @GET("movies/movies")
    fun getUpcomingMovies(@Query("page") page: Int): Single<RemoteMoviesResponse>

    @GET("movies/movies/{id}")
    fun getSingleMovie(@Path("id") id: String): Single<RemoteMovieDetail>

}
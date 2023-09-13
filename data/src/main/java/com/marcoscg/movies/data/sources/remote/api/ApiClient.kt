package com.marcoscg.movies.data.sources.remote.api

import com.marcoscg.movies.data.sources.remote.auth.MoviesAuthInterceptor
import com.marcoscg.movies.data.sources.remote.service.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "http://192.119.19.104:5001/api/"
//    private const val BASE_URL = "http://172.16.190.143:5001/api/"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(MoviesAuthInterceptor())
            .connectTimeout(500,TimeUnit.MILLISECONDS)
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(500,TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build())
        .build()

    fun movieService(): MovieService = retrofit.create(
        MovieService::class.java)

}
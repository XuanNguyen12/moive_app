package com.kma.movies.data.sources.remote.auth

import com.kma.movies.data.BuildConfig
import com.kma.movies.data.sources.remote.CommonSharedPreferences
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class MoviesAuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
            .build()

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + CommonSharedPreferences.getInstance().getTokenUser())
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
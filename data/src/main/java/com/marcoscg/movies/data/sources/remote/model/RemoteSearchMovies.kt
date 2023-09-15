package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteSearchMovies(
    @SerializedName("query" ) var query : String? = null
)

package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteSearchMovies(
    @SerializedName("query" ) var query : String? = null
)

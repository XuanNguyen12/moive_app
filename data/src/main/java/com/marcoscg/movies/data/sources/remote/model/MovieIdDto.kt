package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class MovieIdDto(
    @SerializedName("movieId" ) var movieId : String? = null
)

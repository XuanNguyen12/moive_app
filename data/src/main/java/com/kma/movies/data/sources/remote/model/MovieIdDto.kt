package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class MovieIdDto(
    @SerializedName("movieId" ) var movieId : String? = null
)

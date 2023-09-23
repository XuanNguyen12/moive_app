package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteCheckFavouriteResponse(
    @SerializedName("isFavorite") var isFavorite: Boolean? = null,
)
package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteCheckFavouriteResponse(
    @SerializedName("isFavorite") var isFavorite: Boolean? = null,
)
package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteDeleteFavouriteResponse (
    @SerializedName("message" ) var message : String? = null
)
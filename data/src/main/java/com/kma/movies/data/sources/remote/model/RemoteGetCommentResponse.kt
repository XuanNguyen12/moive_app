package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteGetCommentResponse(
    @SerializedName("userId"    ) var userId    : com.kma.movies.data.sources.remote.model.UserId? = com.kma.movies.data.sources.remote.model.UserId(),
    @SerializedName("movieId"   ) var movieId   : String? = null,
    @SerializedName("content"   ) var content   : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("updatedAt" ) var updatedAt : String? = null,
    @SerializedName("id"        ) var id        : String? = null


)
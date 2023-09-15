package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteGetCommentResponse(

    @SerializedName("_id") var Id: String? = null,
    @SerializedName("userId") var userId: RemoteUserId? = RemoteUserId(),
    @SerializedName("movieId") var movieId: String? = null,
    @SerializedName("content") var content: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("__v") var v: Int? = null

)
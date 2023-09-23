package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemotePostCommentResponse (

  @SerializedName("userId"    ) var userId    : String? = null,
  @SerializedName("movieId"   ) var movieId   : String? = null,
  @SerializedName("content"   ) var content   : String? = null,
  @SerializedName("_id"       ) var Id        : String? = null,
  @SerializedName("createdAt" ) var createdAt : String? = null,
  @SerializedName("updatedAt" ) var updatedAt : String? = null,
  @SerializedName("__v"       ) var _v        : Int?    = null

)
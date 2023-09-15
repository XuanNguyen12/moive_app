package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemotePostComment (
  @SerializedName("movieId" ) var movieId : String? = null,
  @SerializedName("content" ) var content : String? = null

)
package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteFavouriteResponse (
  @SerializedName("movieId" ) var movieId : String? = null

)
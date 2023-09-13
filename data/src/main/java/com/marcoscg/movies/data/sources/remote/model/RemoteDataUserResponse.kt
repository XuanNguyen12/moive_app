package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteDataUserResponse (

  @SerializedName("username" ) var username : String? = null,
  @SerializedName("email"    ) var email    : String? = null,
  @SerializedName("id"       ) var id       : String? = null

)
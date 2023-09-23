package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteUserLogin (
  @SerializedName("email"    ) var email    : String? = null,
  @SerializedName("password" ) var password : String? = null
)
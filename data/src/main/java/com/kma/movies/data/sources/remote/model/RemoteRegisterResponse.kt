package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteRegisterResponse (
  @SerializedName("_id"   ) var id    : String? = null,
  @SerializedName("email" ) var email : String? = null
)
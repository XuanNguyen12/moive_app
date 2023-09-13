package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteRegisterResponse (
  @SerializedName("title"   ) var title   : String? = null,
  @SerializedName("message" ) var message : String? = null
)
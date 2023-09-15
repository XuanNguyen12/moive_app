package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class RemoteUserId (
  @SerializedName("_id"      ) var Id       : String? = null,
  @SerializedName("username" ) var username : String? = null

)
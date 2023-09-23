package com.kma.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName


data class UserId (
  @SerializedName("_id"      ) var Id       : String? = null,
  @SerializedName("username" ) var username : String? = null

)
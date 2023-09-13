package com.marcoscg.movies.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteLoginResponse (

  @SerializedName("accessToken" ) var accessToken : String? = null

)
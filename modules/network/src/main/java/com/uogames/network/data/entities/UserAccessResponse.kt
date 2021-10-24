package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

data class UserAccessResponse(
    @SerializedName("access_key")
    val accessKey: String?
)
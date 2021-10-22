package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

public data class LoginResponse(
    @SerializedName("access_key")
    val accessKey: String?
)
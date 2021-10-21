package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

public data class RegisterResponse(
    @SerializedName("access_key")
    val accessKey: String?,
    @SerializedName("status")
    val status: StatusResponse
)
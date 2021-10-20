package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

public data class StatusResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("timestamp")
    val timestamp: Long?,
    @SerializedName("type")
    val type: TypeResponse
)
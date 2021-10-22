package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

data class HealthResponse(
    @SerializedName("health")
    val health: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("timeUpdate")
    val timeUpdate: Long?
)
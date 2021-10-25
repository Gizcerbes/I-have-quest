package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

data class HealthResponse(
    @SerializedName("health")
    val health: Long?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("timeUpdate")
    val timeUpdate: Long?
)
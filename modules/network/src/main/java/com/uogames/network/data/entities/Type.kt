package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

public data class TypeResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("value")
    val value: Int?
)
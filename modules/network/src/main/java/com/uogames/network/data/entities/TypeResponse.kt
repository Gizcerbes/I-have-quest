package com.uogames.network.data.entities


import com.google.gson.annotations.SerializedName

public data class Type(
    @SerializedName("description")
    val description: String?,
    @SerializedName("value")
    val value: Int?
)
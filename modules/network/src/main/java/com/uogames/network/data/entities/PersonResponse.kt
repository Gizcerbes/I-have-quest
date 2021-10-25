package com.uogames.network.data.entities

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?
)
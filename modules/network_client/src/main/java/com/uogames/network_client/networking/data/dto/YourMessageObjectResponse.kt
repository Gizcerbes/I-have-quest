package com.uogames.networking.data.dto

import com.google.gson.annotations.SerializedName

data class YourMessageObjectResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("id_author")
    val idAuthor: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("time")
    val time: Long?,
    @SerializedName("name")
    val name:String?
)
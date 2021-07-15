package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("userKey")
    val userKey: String?
)
package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class UserObjectResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("userKey")
    val userKey: String?
)
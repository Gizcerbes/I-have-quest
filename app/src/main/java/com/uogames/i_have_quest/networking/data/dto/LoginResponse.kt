package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: StatusResponse?,
    @SerializedName("user")
    val user: UserResponse?
)
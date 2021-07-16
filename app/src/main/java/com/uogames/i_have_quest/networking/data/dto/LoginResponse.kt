package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: StatusObjectResponse?,
    @SerializedName("user")
    val user: UserObjectResponse?,
    @SerializedName("person")
    val person: PersonObjectResponse?
)
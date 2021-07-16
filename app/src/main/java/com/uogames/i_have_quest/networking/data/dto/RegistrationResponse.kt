package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("status")
    val status: StatusObjectResponse?
)
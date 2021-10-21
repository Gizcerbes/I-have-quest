package com.uogames.i_have_quest.networking.data.responses


import com.google.gson.annotations.SerializedName
import com.uogames.i_have_quest.networking.data.dto.PersonObjectResponse
import com.uogames.i_have_quest.networking.data.dto.StatusObjectResponse
import com.uogames.i_have_quest.networking.data.dto.UserObjectResponse

data class LoginResponse(
    @SerializedName("status")
    val status: StatusObjectResponse?,
    @SerializedName("user")
    val user: UserObjectResponse?,
    @SerializedName("person")
    val person: PersonObjectResponse?
)
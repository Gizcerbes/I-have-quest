package com.uogames.i_have_quest.networking.data.responses


import com.google.gson.annotations.SerializedName
import com.uogames.i_have_quest.networking.data.dto.PersonObjectResponse
import com.uogames.i_have_quest.networking.data.dto.StatusObjectResponse

data class PersonResponse(
    @SerializedName("person")
    val person: PersonObjectResponse?,
    @SerializedName("status")
    val status: StatusObjectResponse?
)
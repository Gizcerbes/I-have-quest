package com.uogames.i_have_quest.networking.data.responses


import com.google.gson.annotations.SerializedName
import com.uogames.i_have_quest.networking.data.dto.CharacteristicsObjectResponse
import com.uogames.i_have_quest.networking.data.dto.StatusObjectResponse

data class CharacteristicsResponse(
    @SerializedName("characteristics")
    val characteristics: CharacteristicsObjectResponse?,
    @SerializedName("status")
    val status: StatusObjectResponse?
)
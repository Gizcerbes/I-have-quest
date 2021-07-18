package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class CharacteristicsResponse(
    @SerializedName("characteristics")
    val characteristics: CharacteristicsObjectResponse?,
    @SerializedName("status")
    val status: StatusObjectResponse?
)
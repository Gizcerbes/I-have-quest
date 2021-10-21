package com.uogames.networking.data.responses


import com.google.gson.annotations.SerializedName
import com.uogames.i_have_quest.networking.data.dto.StatusObjectResponse
import com.uogames.networking.data.dto.YourMessageObjectResponse

data class YourMessageResponse(
    @SerializedName("status")
    val status: StatusObjectResponse?,
    @SerializedName("your_message")
    val yourMessage: YourMessageObjectResponse?
)
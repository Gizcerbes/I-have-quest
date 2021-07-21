package com.uogames.i_have_quest.networking.data.responses


import com.google.gson.annotations.SerializedName
import com.uogames.i_have_quest.networking.data.dto.ChatInfoObjectResponse
import com.uogames.i_have_quest.networking.data.dto.StatusObjectResponse

data class ChatInfoResponse(
    @SerializedName("chat_info")
    val chatInfo: ChatInfoObjectResponse?,
    @SerializedName("status")
    val status: StatusObjectResponse?
)
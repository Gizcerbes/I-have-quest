package com.uogames.networking.data.responses


import com.google.gson.annotations.SerializedName
import com.uogames.i_have_quest.networking.data.dto.StatusObjectResponse

data class CountResponse(
    @SerializedName("chats_count")
    val chatsCount: Number?,
    @SerializedName("status")
    val status: StatusObjectResponse?
)
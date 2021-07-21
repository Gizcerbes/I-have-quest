package com.uogames.i_have_quest.networking.data.dto

import com.google.gson.annotations.SerializedName

data class ChatInfoObjectResponse(
    @SerializedName("count_messages")
    val countMessages: Long?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("id_author")
    val idAuthor: Long?,
    @SerializedName("id_receiver")
    val idReceiver: Long?,
    @SerializedName("last_message")
    val lastMessage: String?,
    @SerializedName("last_update")
    val lastUpdate: Long?,
    @SerializedName("name_chat")
    val nameChat: String?
)

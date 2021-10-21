package com.uogames.data.entities.objectData

data class ChatInfoObjectData(
    val countMessages: Long,
    val id: Long,
    val idAuthor: Long,
    val idReceiver: Long,
    val lastMessage: String,
    val lastUpdate: Long,
    val nameChat: String
)

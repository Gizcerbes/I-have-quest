package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.ChatInfoObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.ChatInfoObjectResponse

class ChatInfoObjectMapper : Mapper<ChatInfoObjectResponse, ChatInfoObjectData> {
    override fun map(from: ChatInfoObjectResponse): ChatInfoObjectData {
        return ChatInfoObjectData(
            id = from.id ?:0,
            countMessages = from.countMessages ?: 0,
            idAuthor = from.idAuthor ?: 0,
            idReceiver = from.idReceiver ?: 0,
            lastMessage = from.lastMessage.orEmpty(),
            lastUpdate = from.lastUpdate ?: 0,
            nameChat = from.nameChat.orEmpty()
        )
    }
}
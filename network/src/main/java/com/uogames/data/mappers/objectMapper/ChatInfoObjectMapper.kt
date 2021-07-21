package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.ChatInfoObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.ChatInfoObjectResponse

class ChatInfoObjectMapper : Mapper<ChatInfoObjectResponse, ChatInfoObjectData> {
    override fun map(from: ChatInfoObjectResponse): ChatInfoObjectData {
        return ChatInfoObjectData(
            id = from.id,
            countMessages = from.countMessages,
            idAuthor = from.idAuthor,
            idReceiver = from.idReceiver,
            lastMessage = from.lastMessage,
            lastUpdate = from.lastUpdate,
            nameChat = from.nameChat
        )
    }
}
package com.uogames.data.mappers.responseMapper

import com.uogames.data.entities.responseData.ChatInfoData
import com.uogames.data.mappers.Mapper
import com.uogames.data.mappers.objectMapper.ChatInfoObjectMapper
import com.uogames.data.mappers.objectMapper.StatusObjectMapper
import com.uogames.i_have_quest.networking.data.responses.ChatInfoResponse

class ChatInfoMapper : Mapper<ChatInfoResponse, ChatInfoData> {
    override fun map(from: ChatInfoResponse): ChatInfoData {
        return ChatInfoData(
            from.chatInfo?.let { ChatInfoObjectMapper().map(it) },
            from.status?.let { StatusObjectMapper().map(it) }
        )
    }
}
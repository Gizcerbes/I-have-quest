package com.uogames.data.mappers.responseMapper

import com.uogames.data.entities.responseData.YourMessageData
import com.uogames.data.mappers.Mapper
import com.uogames.data.mappers.objectMapper.StatusObjectMapper
import com.uogames.data.mappers.objectMapper.YourMessageObjectMapper
import com.uogames.networking.data.responses.YourMessageResponse

class YourMessageMapper : Mapper<YourMessageResponse, YourMessageData> {
    override fun map(from: YourMessageResponse): YourMessageData {
        return YourMessageData(
            status = from.status?.let { StatusObjectMapper().map(it) },
            yourMessage = from.yourMessage?.let { YourMessageObjectMapper().map(it) }
        )
    }
}
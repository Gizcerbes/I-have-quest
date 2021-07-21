package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.StatusObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.StatusObjectResponse

class StatusObjectMapper : Mapper<StatusObjectResponse, StatusObjectData> {
    override fun map(from: StatusObjectResponse): StatusObjectData {
        return StatusObjectData(
            message = from.message,
            statusCode = from.statusCode,
            timestamp = from.timestamp
        )
    }
}
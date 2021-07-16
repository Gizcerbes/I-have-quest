package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.StatusObjectData
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
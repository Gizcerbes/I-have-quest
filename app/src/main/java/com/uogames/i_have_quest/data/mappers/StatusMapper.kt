package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.StatusData
import com.uogames.i_have_quest.networking.data.dto.StatusResponse

class StatusMapper : Mapper<StatusResponse, StatusData> {
    override fun map(from: StatusResponse): StatusData {
        return StatusData(
            message = from.message,
            statusCode = from.statusCode,
            timestamp = from.timestamp
        )
    }
}
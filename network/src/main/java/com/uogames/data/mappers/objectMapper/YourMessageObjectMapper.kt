package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.YourMessageObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.networking.data.dto.YourMessageObjectResponse

class YourMessageObjectMapper : Mapper<YourMessageObjectResponse, YourMessageObjectData> {
    override fun map(from: YourMessageObjectResponse): YourMessageObjectData {
        return YourMessageObjectData(
            idAuthor = from.idAuthor ?: 0,
            id = from.id ?: 0,
            message = from.message.orEmpty(),
            time = from.time ?: 0,
            name = from.name.orEmpty()
        )
    }
}
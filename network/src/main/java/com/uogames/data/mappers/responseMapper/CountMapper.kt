package com.uogames.data.mappers.responseMapper

import com.uogames.data.entities.responseData.CountData
import com.uogames.data.mappers.Mapper
import com.uogames.data.mappers.objectMapper.StatusObjectMapper
import com.uogames.networking.data.responses.CountResponse

class CountMapper : Mapper<CountResponse, CountData> {
    override fun map(from: CountResponse): CountData {
        return CountData(
            chatsCount = from.chatsCount ?: 0,
            status = from.status?.let { StatusObjectMapper().map(it) }
        )
    }
}
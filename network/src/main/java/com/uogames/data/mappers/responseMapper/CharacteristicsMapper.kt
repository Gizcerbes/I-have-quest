package com.uogames.data.mappers.responseMapper

import com.uogames.data.entities.responseData.CharacteristicsData
import com.uogames.data.mappers.Mapper
import com.uogames.data.mappers.objectMapper.CharacteristicObjectMapper
import com.uogames.data.mappers.objectMapper.StatusObjectMapper
import com.uogames.i_have_quest.networking.data.responses.CharacteristicsResponse

class CharacteristicsMapper : Mapper<CharacteristicsResponse, CharacteristicsData> {
    override fun map(from: CharacteristicsResponse): CharacteristicsData {
        return CharacteristicsData(
            characteristics = from.characteristics?.let { CharacteristicObjectMapper().map(it) },
            status = from.status?.let { StatusObjectMapper().map(it) }
        )
    }
}
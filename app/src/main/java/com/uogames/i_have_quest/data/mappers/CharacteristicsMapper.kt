package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.CharacteristicsData
import com.uogames.i_have_quest.networking.data.dto.CharacteristicsResponse

class CharacteristicsMapper : Mapper<CharacteristicsResponse, CharacteristicsData> {
    override fun map(from: CharacteristicsResponse): CharacteristicsData {
        return CharacteristicsData(
            characteristics = from.characteristics?.let { CharacteristicObjectMapper().map(it) },
            status = from.status?.let { StatusObjectMapper().map(it) }
        )
    }
}
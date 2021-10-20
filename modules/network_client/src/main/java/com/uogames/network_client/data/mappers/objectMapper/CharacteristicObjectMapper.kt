package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.CharacteristicsObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.CharacteristicsObjectResponse

class CharacteristicObjectMapper :
    Mapper<CharacteristicsObjectResponse, CharacteristicsObjectData> {
    override fun map(from: CharacteristicsObjectResponse): CharacteristicsObjectData {
        return CharacteristicsObjectData(
            agility = from.agility ?: 0,
            defence = from.defence ?: 0,
            force = from.force ?: 0,
            health = from.health ?: 0,
            id = from.id ?:0
        )
    }


}
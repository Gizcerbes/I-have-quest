package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.CharacteristicsObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.CharacteristicsObjectResponse

class CharacteristicObjectMapper :
    Mapper<CharacteristicsObjectResponse, CharacteristicsObjectData> {
    override fun map(from: CharacteristicsObjectResponse): CharacteristicsObjectData {
        return CharacteristicsObjectData(
            agility = from.agility,
            defence = from.defence,
            force = from.force,
            health = from.health,
            id = from.id
        )
    }


}
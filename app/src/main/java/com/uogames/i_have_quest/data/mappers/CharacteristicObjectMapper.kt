package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.CharacteristicsObjectData
import com.uogames.i_have_quest.networking.data.dto.CharacteristicsObjectResponse
import com.uogames.i_have_quest.networking.data.dto.CharacteristicsResponse

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
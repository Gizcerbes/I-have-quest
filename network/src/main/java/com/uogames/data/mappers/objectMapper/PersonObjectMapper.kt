package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.PersonObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.PersonObjectResponse

class PersonObjectMapper : Mapper<PersonObjectResponse, PersonObjectData> {
    override fun map(from: PersonObjectResponse): PersonObjectData {
        return PersonObjectData(
            id = from.id ?: 0,
            experience = from.experience ?: 0,
            image = from.image ?: 0,
            lvl = from.lvl ?: 0,
            personName = from.personName.orEmpty(),
            title = from.title.orEmpty(),
            nextLvl = from.nextLvl ?: 0
        )
    }
}
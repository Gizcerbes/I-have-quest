package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.PersonObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.PersonObjectResponse

class PersonObjectMapper : Mapper<PersonObjectResponse, PersonObjectData> {
    override fun map(from: PersonObjectResponse): PersonObjectData {
        return PersonObjectData(
            id = from.id,
            experience = from.experience,
            image = from.image,
            lvl = from.lvl,
            personName = from.personName,
            title = from.title,
            nextLvl = from.nextLvl
        )
    }
}
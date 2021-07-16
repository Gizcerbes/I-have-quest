package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.PersonObjectData
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
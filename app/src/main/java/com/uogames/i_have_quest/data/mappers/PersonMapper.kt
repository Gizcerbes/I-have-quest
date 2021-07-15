package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.PersonData
import com.uogames.i_have_quest.networking.data.dto.PersonResponse

class PersonMapper : Mapper<PersonResponse, PersonData> {
    override fun map(from: PersonResponse): PersonData {
        return PersonData(
            id = from.id,
            experience = from.experience,
            image = from.image,
            lvl = from.lvl,
            personName = from.personName,
            title = from.title
        )
    }
}
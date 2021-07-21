package com.uogames.data.mappers.responseMapper

import com.uogames.data.entities.responseData.PersonData
import com.uogames.data.mappers.Mapper
import com.uogames.data.mappers.objectMapper.PersonObjectMapper
import com.uogames.data.mappers.objectMapper.StatusObjectMapper
import com.uogames.i_have_quest.networking.data.responses.PersonResponse

class PersonMapper : Mapper<PersonResponse, PersonData> {
    override fun map(from: PersonResponse): PersonData {
        return PersonData(
            status = from.status?.let { StatusObjectMapper().map(it) },
            person = from.person?.let { PersonObjectMapper().map(it) }
        )
    }
}
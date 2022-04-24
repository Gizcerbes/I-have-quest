package com.uogames.network.data.mappers

import com.uogames.dto.Person
import com.uogames.network.data.entities.PersonResponse

object PersonMapper : Mapper<PersonResponse, Person> {

    override fun PersonResponse.toDTO(): Person {
        return Person(
            id = id ?: 0,
            name = name.orEmpty(),
            title = title.orEmpty()
        )
    }

}
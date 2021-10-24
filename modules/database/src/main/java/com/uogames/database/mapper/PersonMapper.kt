package com.uogames.database.mapper

import com.uogames.database.dto.Person
import com.uogames.database.entity.PersonEntity

object PersonMapper : Mapper<PersonEntity, Person> {
    override fun PersonEntity.toDTO(): Person {
        return Person(id, name, title)
    }

    override fun Person.toEntity(): PersonEntity {
        return PersonEntity(id, name, title)
    }
}
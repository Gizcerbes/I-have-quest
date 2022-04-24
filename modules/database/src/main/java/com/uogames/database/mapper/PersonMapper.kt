package com.uogames.database.mapper

import com.uogames.database.entity.PersonEntity
import com.uogames.dto.Person

object PersonMapper : Mapper<PersonEntity, Person> {
    override fun PersonEntity.toDTO(): Person {
        return Person(id, name, title)
    }

    override fun Person.toEntity(): PersonEntity {
        return PersonEntity(id, name, title)
    }
}
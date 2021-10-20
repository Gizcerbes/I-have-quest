package com.uogames.model.mappers

import android.app.Person
import com.uogames.network.data.dto.PersonDTO

object NetworkPersonMapper: Mapper<PersonDTO, Person> {
    override fun PersonDTO.toDTO(): Person {
        return 
    }
}
package com.uogames.network.data.mappers

import com.uogames.network.data.dto.PersonsSearch
import com.uogames.network.data.entities.PersonSearchResponse
import com.uogames.network.data.mappers.PersonMapper.toDTO

object PersonSearchMapper : Mapper<PersonSearchResponse, PersonsSearch> {
    override fun PersonSearchResponse.toDTO(): PersonsSearch {
        val map = this
        return PersonsSearch().apply { addAll(map.map { it.toDTO() }) }
    }
}
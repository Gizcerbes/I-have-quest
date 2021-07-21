package com.uogames.data.mappers.responseMapper

import com.uogames.data.entities.responseData.RegistrationData
import com.uogames.data.mappers.Mapper
import com.uogames.data.mappers.objectMapper.StatusObjectMapper
import com.uogames.i_have_quest.networking.data.responses.RegistrationResponse

class RegistrationMapper : Mapper<RegistrationResponse, RegistrationData> {
    private val statusMapper = StatusObjectMapper()

    override fun map(from: RegistrationResponse): RegistrationData {
        return RegistrationData(
            status = from.status?.let { statusMapper.map(it) }
        )
    }
}
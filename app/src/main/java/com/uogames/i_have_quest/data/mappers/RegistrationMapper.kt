package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.RegistrationData
import com.uogames.i_have_quest.networking.data.dto.RegistrationResponse

class RegistrationMapper : Mapper<RegistrationResponse, RegistrationData> {
    private val statusMapper = StatusObjectMapper()

    override fun map(from: RegistrationResponse): RegistrationData {
        return RegistrationData(
            status = from.status?.let { statusMapper.map(it) }
        )
    }
}
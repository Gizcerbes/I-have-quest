package com.uogames.data.mappers.responseMapper

import com.uogames.data.entities.responseData.LoginData
import com.uogames.data.mappers.Mapper
import com.uogames.data.mappers.objectMapper.PersonObjectMapper
import com.uogames.data.mappers.objectMapper.StatusObjectMapper
import com.uogames.data.mappers.objectMapper.UserObjectMapper
import com.uogames.i_have_quest.networking.data.responses.LoginResponse

class LoginMapper : Mapper<LoginResponse, LoginData> {
    private val statusMapper = StatusObjectMapper()
    private val userMapper = UserObjectMapper()
    private val personMapper = PersonObjectMapper()

    override fun map(from: LoginResponse): LoginData {
        return LoginData(
            status = from.status?.let { statusMapper.map(it) },
            user = from.user?.let { userMapper.map(it) },
            person = from.person?.let { personMapper.map(it) }
        )
    }
}
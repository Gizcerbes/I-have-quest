package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.LoginData
import com.uogames.i_have_quest.networking.data.dto.LoginResponse

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
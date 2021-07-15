package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.LoginData
import com.uogames.i_have_quest.networking.data.dto.LoginResponse

class LoginMapper : Mapper<LoginResponse, LoginData> {
    private val statusMapper = StatusMapper()
    private val userMapper = UserMapper()

    override fun map(from: LoginResponse): LoginData {
        return LoginData(
            status = from.status?.let { statusMapper.map(it) },
            user = from.user?.let { userMapper.map(it) }
        )
    }
}
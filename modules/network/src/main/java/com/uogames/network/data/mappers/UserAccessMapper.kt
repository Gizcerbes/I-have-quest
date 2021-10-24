package com.uogames.network.data.mappers

import com.uogames.network.data.dto.UserAccess
import com.uogames.network.data.entities.UserAccessResponse

 object UserAccessMapper : Mapper<UserAccessResponse, UserAccess> {
    override fun UserAccessResponse.toDTO(): UserAccess {
        return UserAccess(
            accessKey = accessKey.orEmpty()
        )
    }
}
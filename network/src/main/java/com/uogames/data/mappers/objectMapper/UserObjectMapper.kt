package com.uogames.data.mappers.objectMapper

import com.uogames.data.entities.objectData.UserObjectData
import com.uogames.data.mappers.Mapper
import com.uogames.i_have_quest.networking.data.dto.UserObjectResponse

class UserObjectMapper: Mapper<UserObjectResponse, UserObjectData> {
    override fun map(from: UserObjectResponse): UserObjectData {
        return UserObjectData(
            id = from.id ?: 0,
            name = from.name.orEmpty(),
            userKey = from.userKey.orEmpty()
        )
    }


}
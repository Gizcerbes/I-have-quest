package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.UserObjectData
import com.uogames.i_have_quest.networking.data.dto.UserObjectResponse

class UserObjectMapper: Mapper<UserObjectResponse, UserObjectData> {
    override fun map(from: UserObjectResponse): UserObjectData {
        return UserObjectData(
            id = from.id,
            name = from.name,
            userKey = from.userKey
        )
    }


}
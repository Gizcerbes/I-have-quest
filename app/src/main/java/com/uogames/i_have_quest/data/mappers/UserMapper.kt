package com.uogames.i_have_quest.data.mappers

import com.uogames.i_have_quest.data.entities.UserData
import com.uogames.i_have_quest.networking.data.dto.UserResponse

class UserMapper: Mapper<UserResponse, UserData> {
    override fun map(from: UserResponse): UserData {
        return UserData(
            id = from.id,
            name = from.name,
            userKey = from.userKey
        )
    }


}
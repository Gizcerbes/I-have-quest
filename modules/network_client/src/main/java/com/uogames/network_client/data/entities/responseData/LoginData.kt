package com.uogames.data.entities.responseData

import com.uogames.data.entities.objectData.PersonObjectData
import com.uogames.data.entities.objectData.StatusObjectData
import com.uogames.data.entities.objectData.UserObjectData

data class LoginData(
    val status: StatusObjectData?,
    val user: UserObjectData?,
    val person: PersonObjectData?
)
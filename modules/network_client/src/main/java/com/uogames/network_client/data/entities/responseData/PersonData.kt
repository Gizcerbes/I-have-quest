package com.uogames.data.entities.responseData

import com.uogames.data.entities.objectData.PersonObjectData
import com.uogames.data.entities.objectData.StatusObjectData

data class PersonData(
    val person: PersonObjectData?,
    val status: StatusObjectData?
)
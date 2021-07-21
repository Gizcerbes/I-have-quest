package com.uogames.data.entities.responseData

import com.uogames.data.entities.objectData.CharacteristicsObjectData
import com.uogames.data.entities.objectData.StatusObjectData

data class CharacteristicsData(
    val characteristics: CharacteristicsObjectData?,
    val status: StatusObjectData?
)

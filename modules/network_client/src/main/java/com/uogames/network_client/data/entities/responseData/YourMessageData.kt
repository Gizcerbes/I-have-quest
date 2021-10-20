package com.uogames.data.entities.responseData

import com.uogames.data.entities.objectData.StatusObjectData
import com.uogames.data.entities.objectData.YourMessageObjectData

data class YourMessageData(
    val status: StatusObjectData?,
    val yourMessage: YourMessageObjectData?) {
}
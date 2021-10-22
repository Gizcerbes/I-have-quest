package com.uogames.network.data.dto

import java.util.*

data class StatusDTO(
    val message: String = "OK",
    val timestamp: Long = Date().time,
    val type: TypeDTO = TypeDTO(description = "OK", 200)
)
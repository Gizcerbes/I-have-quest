package com.uogames.network.data.dto

import java.util.*

data class Status(
    val message: String = "OK",
    val timestamp: Long = Date().time,
    val type: Type = Type(description = "OK", 200)
)
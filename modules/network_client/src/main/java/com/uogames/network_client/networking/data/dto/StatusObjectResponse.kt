package com.uogames.i_have_quest.networking.data.dto


import com.google.gson.annotations.SerializedName

data class StatusObjectResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("timestamp")
    val timestamp: String?
)
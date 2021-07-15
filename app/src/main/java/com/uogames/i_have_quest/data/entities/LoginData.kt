package com.uogames.i_have_quest.data.entities

import com.google.gson.annotations.SerializedName
import com.uogames.i_have_quest.networking.data.dto.StatusResponse
import com.uogames.i_have_quest.networking.data.dto.UserResponse

data class LoginData(
    val status: StatusData?,
    val user: UserData?
)
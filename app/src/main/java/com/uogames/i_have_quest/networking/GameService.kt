package com.uogames.i_have_quest.networking

import com.uogames.i_have_quest.data.entities.LoginData
import com.uogames.i_have_quest.networking.data.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface GameService {

    @POST("/login")
    suspend fun login(
        @Field("login")
        login : String,
        @Field("password")
        password : String
    ) : Response<LoginResponse>

}
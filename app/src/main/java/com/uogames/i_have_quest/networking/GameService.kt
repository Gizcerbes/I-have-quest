package com.uogames.i_have_quest.networking

import com.uogames.i_have_quest.networking.data.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GameService {

    @POST("login")
    suspend fun login(
        @Query("login")
        login: String,
        @Query("password")
        password: String
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Query("login")
        login: String,
        @Query("password")
        password: String
    ): Response<RegistrationResponse>

    @GET("person")
    suspend fun getPersonById(
        @Query("userKey")
        userKey: String,
        @Query("id")
        id: String
    ): Response<PersonResponse>

    @GET("person")
    suspend fun getPersonByName(
        @Query("userKey")
        userKey: String,
        @Query("name")
        name: String
    ): Response<PersonResponse>

    @GET("characteristics")
    suspend fun getCharacteristicsById(
        @Query("userKey")
        userKey: String,
        @Query("id")
        id: String
    ) : Response<CharacteristicsResponse>
}
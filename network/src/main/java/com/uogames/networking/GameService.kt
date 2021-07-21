package com.uogames.i_have_quest.networking

import com.uogames.i_have_quest.networking.data.responses.*
import com.uogames.networking.data.responses.CountResponse
import com.uogames.networking.data.responses.YourMessageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
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
    ): Response<CharacteristicsResponse>

    @GET("chatInfo")
    suspend fun getChatInfoByName(
        @Query("userKey")
        userKey: String,
        @Query("chatName")
        chatName: String
    ): Response<ChatInfoResponse>

    @GET("chatInfo")
    suspend fun getChatInfoByNumber(
        @Query("userKey")
        userKey: String,
        @Query("numberChat")
        numberChat: String
    ): Response<ChatInfoResponse>

    @GET("chatcount")
    suspend fun getChatsCount(
        @Query("userKey")
        userKey: String
    ): Response<CountResponse>

    @POST("chatInfo")
    suspend fun sendMessageByChatName(
        @Query("userKey")
        userKey: String,
        @Query("chatName")
        chatName: String,
        @Query("message")
        message: String
    ): Response<YourMessageResponse>

    @POST("chatInfo")
    suspend fun sendMessageByPerson(
        @Query("userKey")
        userKey: String,
        @Query("receiver")
        receiver: String,
        @Query("message")
        message: String
    ): Response<YourMessageResponse>

    @GET("getMessage")
    suspend fun getMessage(
        @Query("userKey")
        userKey: String,
        @Query("chatName")
        chatName: String,
        @Query("numberMessage")
        numberMessage: String
    ): Response<YourMessageResponse>
}
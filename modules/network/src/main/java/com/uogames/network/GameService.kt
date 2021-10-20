package com.uogames.network

import com.uogames.network.data.entities.LoginResponse
import com.uogames.network.data.entities.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

public interface GameService {

	@FormUrlEncoded
	@POST("/user/register")
	suspend fun registration(
		@Field("name") name: String,
		@Field("password") password: String
	): Response<RegisterResponse>

	@FormUrlEncoded
	@POST("/user/login")
	suspend fun login(
		@Field("name") name: String,
		@Field("password") password: String
	): Response<LoginResponse>

}
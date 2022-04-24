package com.uogames.network

import com.uogames.network.data.entities.*
import retrofit2.Response
import retrofit2.http.*

interface GameService {

	@FormUrlEncoded
	@POST("/user/register")
	suspend fun registration(
		@Field("name") name: String,
		@Field("password") password: String
	): Response<UserAccessResponse>

	@FormUrlEncoded
	@POST("/user/login")
	suspend fun login(
		@Field("name") name: String,
		@Field("password") password: String
	): Response<UserAccessResponse>

	@GET("/person/{id}")
	suspend fun getPerson(
		@Header("access_key") key: String,
		@Path("id") id: Long
	): Response<PersonResponse>

	@GET("/person/my")
	suspend fun getMyPerson(
		@Header("access_key") key: String
	): Response<PersonResponse>

	@FormUrlEncoded
	@GET("/person/search")
	suspend fun searchPerson(
		@Header("access_key") key: String,
		@Field("name") name: String
	): Response<PersonSearchResponse>

	@GET("/characteristic/{id}")
	suspend fun getCharacteristic(
		@Header("access_key") key: String,
		@Path("id") id: Long
	): Response<CharacteristicResponse>


	@GET("/characteristic/my")
	suspend fun getMyCharacteristic(
		@Header("access_key") key: String,
	): Response<CharacteristicResponse>

	@GET("/health/{id}")
	suspend fun getHealth(
		@Header("access_key") key: String,
		@Path("id") id: Long
	): Response<HealthResponse>

	@GET("/health/my")
	suspend fun getMyHealth(
		@Header("access_key") key: String
	): Response<HealthResponse>

	@GET("experience/{id}")
	suspend fun getExperience(
		@Header("access_key") key: String,
		@Path("id") id: Long
	): Response<ExperienceResponse>

	@GET("experience/my")
	suspend fun getMyExperience(
		@Header("access_key") key: String
	): Response<ExperienceResponse>

	@FormUrlEncoded
	@POST("location")
	suspend fun setMyLocation(
		@Header("access_key") key: String,
		@Field("latitude") latitude: String,
		@Field("longitude") longitude: String
	)

}
package com.uogames.network

import com.uogames.network.data.dto.UserAccessDTO
import com.uogames.network.data.mappers.LoginMapper.toDTO
import com.uogames.network.data.mappers.RegisterMapper.toDTO
import retrofit2.Response

class Repository {

	private val api = GameAPI.provideRetrofit()

	private fun <T> run(response:Response<T>): T{
		return if (response.isSuccessful) {
			response.body() ?: throw Exception("Not content")
		} else {
			throw Exception(response.message())
		}
	}

	suspend fun registration(user: String, password: String): UserAccessDTO {
		return run(api.registration(user, password)).toDTO()
	}

	suspend fun login(user: String, password: String): UserAccessDTO {
		return run(api.login(user,password)).toDTO()
	}

}
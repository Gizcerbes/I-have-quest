package com.uogames.network

import com.google.gson.Gson
import com.uogames.database.dto.Health
import com.uogames.network.data.dto.*
import com.uogames.network.data.entities.StatusResponse
import com.uogames.network.data.mappers.CharacteristicMapper.toDTO
import com.uogames.network.data.mappers.ExperienceMapper.toDTO
import com.uogames.network.data.mappers.HealthMapper.toDTO
import com.uogames.network.data.mappers.PersonMapper.toDTO
import com.uogames.network.data.mappers.PersonSearchMapper.toDTO
import com.uogames.network.data.mappers.UserAccessMapper.toDTO
import com.uogames.network.data.mappers.StatusMapper.toDTO
import retrofit2.Response
import java.util.*

class Repository {

	private val api = GameAPI.provideRetrofit()

	private inline fun <T, R> run(
		query: () -> Response<T>,
		callback: (any: T?, status: Status) -> R
	): R {
		return try {
			val response = query()
			if (response.isSuccessful) {
				callback(response.body(), Status())
			} else {
				val gson = Gson()
				val status =
					gson.fromJson(response.errorBody()?.charStream(), StatusResponse::class.java)
				callback(null, status.toDTO())
			}
		} catch (e: Exception) {
			callback(
				null,
				Status(e.message.orEmpty(), Date().time, Type(e.message.orEmpty(), 500))
			)
		}
	}

	suspend fun registration(user: String, password: String): ObjectDTO<UserAccess> =
		run({ api.registration(user, password) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun login(user: String, password: String): ObjectDTO<UserAccess> =
		run({ api.login(user, password) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun getPerson(key: String, id: Long): ObjectDTO<Person> =
		run({ api.getPerson(key, id) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun getMyPerson(key: String): ObjectDTO<Person> =
		run({ api.getMyPerson(key) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun searchPerson(key: String, name: String): ObjectDTO<PersonsSearch> =
		run({ api.searchPerson(key, name) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun getCharacteristic(key: String, id: Long): ObjectDTO<Characteristic> =
		run({ api.getCharacteristic(key, id) }) { any, status -> ObjectDTO(any?.toDTO(), status) }

	suspend fun getMyCharacteristic(key: String): ObjectDTO<Characteristic> =
		run({ api.getMyCharacteristic(key) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun getHealth(key: String, id: Long): ObjectDTO<Health> =
		run({ api.getHealth(key, id) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun getMyHealth(key: String): ObjectDTO<Health> =
		run({ api.getMyHealth(key) }) { any, status -> ObjectDTO(any?.toDTO(), status) }


	suspend fun getExperience(key: String, id: Long): ObjectDTO<Experience> =
		run({ api.getExperience(key, id) }) { any, status -> ObjectDTO(any?.toDTO(), status) }

	suspend fun getMyExperience(key: String): ObjectDTO<Experience> =
		run({ api.getMyExperience(key) }) { any, status -> ObjectDTO(any?.toDTO(), status) }

}
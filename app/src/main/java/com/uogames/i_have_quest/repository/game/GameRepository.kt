package com.uogames.i_have_quest.repository.game

import android.util.Log
import com.uogames.i_have_quest.data.entities.LoginData
import com.uogames.i_have_quest.data.entities.PersonData
import com.uogames.i_have_quest.data.entities.PersonObjectData
import com.uogames.i_have_quest.data.entities.RegistrationData
import com.uogames.i_have_quest.data.mappers.LoginMapper
import com.uogames.i_have_quest.data.mappers.PersonMapper
import com.uogames.i_have_quest.data.mappers.PersonObjectMapper
import com.uogames.i_have_quest.data.mappers.RegistrationMapper
import com.uogames.i_have_quest.networking.api.GameApi
import com.uogames.i_have_quest.networking.data.dto.PersonResponse

class GameRepository {

    private val api = GameApi.provideRetrofit()
    private val loginMapper = LoginMapper()
    private val registrationMapper = RegistrationMapper()
    private val personMapper = PersonMapper()

    suspend fun logIn(login: String, password: String): LoginData? {
        val response = api.login(login, password)
        return if (response.isSuccessful) {
            response.body()?.let { loginMapper.map(it) }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }

    suspend fun register(login: String, password: String): RegistrationData? {
        val response = api.register(login, password)
        return if (response.isSuccessful) {
            response.body()?.let { registrationMapper.map(it) }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }

    suspend fun getPersonByID(userKey: String, id: String): PersonData? {
        val response = api.getPersonById(userKey, id)
        return if (response.isSuccessful) {
            response.body()?.let {
                personMapper.map(it)
            }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }

    suspend fun getPersonByName(userKey: String, name: String): PersonData? {
        val response = api.getPersonByName(userKey, name)
        return if (response.isSuccessful) {
            response.body()?.let { personMapper.map(it) }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }

}
package com.uogames.i_have_quest.repository.game

import com.uogames.i_have_quest.data.entities.LoginData
import com.uogames.i_have_quest.data.mappers.LoginMapper
import com.uogames.i_have_quest.networking.api.GameApi

class GameRepository {

    private val api = GameApi.provideRetrofit()
    private val loginMapper = LoginMapper()

    suspend fun logIn(login: String, password: String): LoginData? {
        val response = api.login(login, password)
        return if(response.isSuccessful){
            response.body()?.let { loginMapper.map(it) }
        } else {
            throw Throwable(response.errorBody().toString())
        }
    }


}
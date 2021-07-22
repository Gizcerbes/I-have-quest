package com.uogames.repository.game

import com.uogames.data.entities.responseData.*
import com.uogames.data.mappers.responseMapper.*
import com.uogames.i_have_quest.networking.api.GameApi
import com.uogames.networking.GameRunner

class GameRepository {

    private val api = GameApi.provideRetrofit()
    private val gameRunner = GameRunner
    private val loginMapper = LoginMapper()
    private val registrationMapper = RegistrationMapper()
    private val personMapper = PersonMapper()
    private val characteristicsMapper = CharacteristicsMapper()
    private val chaInfoMapper = ChatInfoMapper()
    private val countMapper = CountMapper()
    private val yourMessageMapper = YourMessageMapper()

    suspend fun logIn(login: String, password: String): LoginData? {
        return gameRunner.run(api.login(login, password), loginMapper)
    }

    suspend fun register(login: String, password: String): RegistrationData? {
        return gameRunner.run(api.register(login, password), registrationMapper)
    }

    suspend fun getPersonByID(userKey: String, id: String): PersonData? {
        return gameRunner.run(api.getPersonById(userKey, id), personMapper)
    }

    suspend fun getPersonByName(userKey: String, name: String): PersonData? {
        return gameRunner.run(api.getPersonByName(userKey, name), personMapper)
    }

    suspend fun getCharacteristicById(userKey: String, id: String): CharacteristicsData? {
        return gameRunner.run(api.getCharacteristicsById(userKey, id), characteristicsMapper)
    }

    suspend fun getChatInfoByName(userKey: String, chatName: String): ChatInfoData? {
        return gameRunner.run(api.getChatInfoByName(userKey, chatName), chaInfoMapper)
    }

    suspend fun getChatInfoByNumber(userKey: String, numberChat: String): ChatInfoData? {
        return gameRunner.run(api.getChatInfoByNumber(userKey, numberChat), chaInfoMapper)
    }

    suspend fun getChatInfoByReceiverID(userKey: String, receiverID: String): ChatInfoData? {
        return gameRunner.run(api.getChatInfoByReceiverID(userKey, receiverID), chaInfoMapper)
    }

    suspend fun getChatsCount(userKey: String): CountData? {
        return gameRunner.run(api.getChatsCount(userKey), countMapper)
    }

    suspend fun sendMessageByChatName(userKey: String,chatName: String,message:String): YourMessageData? {
        return gameRunner.run(api.sendMessageByChatName(userKey,chatName,message),yourMessageMapper)
    }

    suspend fun sendMessageByPerson(userKey: String, receiver: String, message: String): YourMessageData? {
        return gameRunner.run(api.sendMessageByPerson(userKey,receiver,message), yourMessageMapper)
    }

    suspend fun getMessageByChatNameAndNumber(userKey: String, chatName: String, numberMessage:String): YourMessageData? {
        return gameRunner.run(api.getMessage(userKey,chatName,numberMessage),yourMessageMapper)
    }

}
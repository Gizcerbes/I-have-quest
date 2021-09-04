package com.uogames.i_have_quest.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.data.entities.objectData.CharacteristicsObjectData
import com.uogames.data.entities.objectData.PersonObjectData
import com.uogames.data.entities.responseData.*
import com.uogames.repository.game.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NetworkModel : ViewModel() {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val gameRepository = GameRepository()

    private val _loginData = MutableLiveData<LoginData>()
    val loginData: LiveData<LoginData> = _loginData

    private val _personData = MutableLiveData<PersonObjectData>()
    val personData: LiveData<PersonObjectData> = _personData

    private val _characteristicsData = MutableLiveData<CharacteristicsObjectData>()
    val characteristicsData: LiveData<CharacteristicsObjectData> = _characteristicsData

    private val _countPersonalChats = MutableLiveData<Number>(0)
    val countPersonalChats: LiveData<Number> = _countPersonalChats

    private val _countForumChats = MutableLiveData<Number>(0)
    val countForumChats: LiveData<Number> = _countForumChats


    fun logIn(login: String, password: String, block: (LoginData) -> Unit) {
        ioScope.launch {
            try {
                gameRepository.logIn(login, password)?.let {
                    _loginData.postValue(it)
                    it.person?.let { person -> _personData.postValue(person) }
                    mainScope.launch { block(it) }
                }
            } catch (e: Throwable) {

            }
        }
    }

    fun register(login: String, password: String, block: (RegistrationData) -> Unit) {
        ioScope.launch {
            try {
                gameRepository.register(login, password)?.let {
                    mainScope.launch { block(it) }
                }
            } catch (e: Throwable) {

            }
        }
    }

    fun updateMyPerson() {
        ioScope.launch {
            try {
                loginData.value?.let {
                    gameRepository.getPersonByID(
                        it.user?.userKey.toString(),
                        it.user?.id.toString()
                    )
                        ?.let { result ->
                            _personData.postValue(result.person)
                        }
                }
            } catch (e: Throwable) {

            }
        }
    }

    fun getPersonById(id: Int?, block: (PersonData) -> Unit) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val data = gameRepository.getPersonByID(myKey.toString(), id.toString())
                data?.let { mainScope.launch { block(it) } }
            } catch (e: Throwable) {

            }
        }
    }


    fun updateCharacteristics() {
        ioScope.launch {
            try {
                loginData.value?.let {
                    val result = gameRepository.getCharacteristicById(
                        it.user?.userKey.toString(),
                        it.user?.id.toString()
                    )
                    result?.let { res -> _characteristicsData.postValue(res.characteristics) }
                }
            } catch (e: Throwable) {

            }
        }
    }

    fun getCharacteristicsById(idPerson: Int, block: (CharacteristicsObjectData) -> Unit) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val result =
                    gameRepository.getCharacteristicById(myKey.toString(), idPerson.toString())
                mainScope.launch { result?.characteristics?.let { block(it) } }
            } catch (e: Throwable) {

            }
        }
    }

    fun getChatInfoByName(chatName: String, callback: (ChatInfoData) -> Unit) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val data = gameRepository.getChatInfoByName(myKey.toString(), chatName)
                mainScope.launch { data?.let { callback(it) } }
            } catch (e: Throwable) {

            }
        }
    }

    fun getChatInfoByNumber(number: Int, callback: (ChatInfoData) -> Unit) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val data = gameRepository.getChatInfoByNumber(myKey.toString(), number.toString())
                mainScope.launch { data?.let { callback(it) } }
            } catch (e: Throwable) {

            }
        }
    }

    fun getChatInfoByReceiverID(receiverID: Int, callback: (ChatInfoData) -> Unit) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val data =
                    gameRepository.getChatInfoByReceiverID(myKey.toString(), receiverID.toString())
                mainScope.launch { data?.let { callback(it) } }
            } catch (e: Throwable) {

            }
        }
    }

    fun updateCountPersonalChats() {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                _countPersonalChats.postValue(myKey?.let { gameRepository.getChatsCount(it) }?.chatsCount)
            } catch (e: Throwable) {

            }
        }
    }

    fun updateCountForumChats() {
        _countForumChats.postValue(0)
    }

    fun sendMessageByChatName(chatName: String, message: String, block: (YourMessageData) -> Unit) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val result =
                    gameRepository.sendMessageByChatName(myKey.toString(), chatName, message)
                mainScope.launch { result?.let { block(it) } }
            } catch (e: Throwable) {

            }
        }
    }

    fun sendMessageByPerson(
        idReceiver: Int,
        message: String,
        block: (YourMessageData) -> Unit
    ) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val result = gameRepository.sendMessageByPerson(
                    myKey.toString(),
                    idReceiver.toString(),
                    message
                )
                mainScope.launch { result?.let { block(it) } }
            } catch (e: Throwable) {

            }
        }
    }

    fun getMessage(chatName: String, number: Int, block: (YourMessageData) -> Unit) {
        ioScope.launch {
            try {
                val myKey = loginData.value?.user?.userKey
                val result = gameRepository.getMessageByChatNameAndNumber(
                    myKey.toString(),
                    chatName,
                    number.toString()
                )
                mainScope.launch { result?.let { block(it) } }
            } catch (e: Throwable) {

            }
        }
    }


}
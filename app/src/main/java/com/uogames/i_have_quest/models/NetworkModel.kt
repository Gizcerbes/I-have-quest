package com.uogames.i_have_quest.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.i_have_quest.data.entities.CharacteristicsObjectData
import com.uogames.i_have_quest.data.entities.LoginData
import com.uogames.i_have_quest.data.entities.PersonObjectData
import com.uogames.i_have_quest.data.entities.RegistrationData
import com.uogames.i_have_quest.repository.game.GameRepository
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


    fun logIn(login: String, password: String, block: (LoginData) -> Unit) {
        ioScope.launch {
            try {
                gameRepository.logIn(login, password)?.let {
                    _loginData.postValue(it)
                    _personData.postValue(it.person)
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


}
package com.uogames.i_have_quest.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


    fun logIn(login: String, password: String) {
        ioScope.launch {
            gameRepository.logIn(login, password)?.let {
                _loginData.postValue(it)
                _personData.postValue(it.person)
            }
        }
    }

    fun register(login: String, password: String, block: (RegistrationData) -> Unit) {
        ioScope.launch {
            gameRepository.register(login, password)?.let {
                mainScope.launch { block(it) }
            }
        }
    }

    fun updateMyPerson() {
        ioScope.launch {
            loginData.value?.let {
                Log.e("TAG", it.user?.userKey.toString())
                Log.e("TAG", it.user?.id.toString())
                gameRepository.getPersonByID(it.user?.userKey.toString(), it.user?.id.toString())
                    ?.let { result ->
                        Log.e("TAG", result.toString())
                        _personData.postValue(result.person)
                    }
            }
        }
    }


}
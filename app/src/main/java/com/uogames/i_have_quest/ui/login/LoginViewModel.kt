package com.uogames.i_have_quest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val game: GameProvider) : ViewModel() {

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username



    fun setUsername(username: String) {
        _username.value = username
    }

}
package com.uogames.i_have_quest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val game: GameProvider) : ViewModel() {

    enum class Errors {
        OK, SHORT, LONG
    }


    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username
    private val _usernameError = MutableLiveData(Errors.OK)
    val usernameError: LiveData<Errors> = _usernameError

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password
    private val _passwordError = MutableLiveData(Errors.OK)
    val passwordError: LiveData<Errors> = _passwordError

    private val _busy = MutableLiveData(false)
    val busy: LiveData<Boolean> = _busy

    fun setUsername(username: String) {
        _username.value = username
        _usernameError.value = Errors.OK
    }

    fun setPassword(password: String) {
        _password.value = password
        _passwordError.value = Errors.OK
    }

    fun login(result: (Boolean) -> Unit = {}) {
        _busy.value = true

        val username = username.value
        val password = password.value

        if (username.isNullOrEmpty() || username.length < 3) {
            _usernameError.value = Errors.SHORT
        } else if (username.length > 13) {
            _usernameError.value = Errors.LONG
        }

        if (password.isNullOrEmpty() || password.length < 5) {
            _passwordError.value = Errors.SHORT
        } else if (password.length > 31) {
            _passwordError.value = Errors.LONG
        }

        if (
            usernameError.value == Errors.OK &&
            passwordError.value == Errors.OK
        ) {
            game.login(username.orEmpty(), password.orEmpty()) {
                result(it)
                _busy.value = false
            }
        } else {
            _busy.value = false
        }

    }

}
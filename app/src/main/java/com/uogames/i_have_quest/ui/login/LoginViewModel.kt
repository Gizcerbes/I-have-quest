package com.uogames.i_have_quest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val game: GameProvider) : ViewModel() {

	enum class Errors {
		DEFAULT, SHORT, LONG
	}


	private val _username = MutableStateFlow("")
	val username: StateFlow<String> = _username.asStateFlow()
	private val _usernameError = MutableStateFlow(Errors.DEFAULT)
	val usernameError: StateFlow<Errors> = _usernameError.asStateFlow()

	private val _password = MutableStateFlow("")
	val password: StateFlow<String> = _password.asStateFlow()
	private val _passwordError = MutableStateFlow(Errors.DEFAULT)
	val passwordError: StateFlow<Errors> = _passwordError.asStateFlow()

	private val _busy = MutableStateFlow(false)
	val busy: StateFlow<Boolean> = _busy.asStateFlow()

	fun setUsername(username: String) {
		_username.value = username
		_usernameError.value = Errors.DEFAULT
	}

	fun setPassword(password: String) {
		_password.value = password
		_passwordError.value = Errors.DEFAULT
	}

	fun login(result: (message: String, code: Int) -> Unit = { _, _ -> }) {
		_busy.value = true

		val username = username.value
		val password = password.value

		if (username.length < 3) {
			_usernameError.value = Errors.SHORT
		} else if (username.length > 13) {
			_usernameError.value = Errors.LONG
		}

		if (password.length < 5) {
			_passwordError.value = Errors.SHORT
		} else if (password.length > 31) {
			_passwordError.value = Errors.LONG
		}

		if (
			usernameError.value == Errors.DEFAULT &&
			passwordError.value == Errors.DEFAULT
		) {
			game.login(username, password) { mess, code ->
				result(mess, code)
				_busy.value = false
			}
		} else {
			_busy.value = false
		}

	}

}
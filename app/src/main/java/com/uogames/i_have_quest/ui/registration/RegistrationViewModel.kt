package com.uogames.i_have_quest.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val repository: GameProvider) :
	ViewModel() {

	enum class Errors {
		DEFAULT, SHORT, LONG, EXIST, NOT_EQUALS
	}

	private val _username = MutableStateFlow("")
	val username: StateFlow<String> = _username.asStateFlow()
	private val _usernameErrorCode = MutableStateFlow(Errors.DEFAULT)
	val usernameErrorCode: StateFlow<Errors> = _usernameErrorCode

	private val _password = MutableStateFlow("")
	val password: StateFlow<String> = _password.asStateFlow()
	private val _passwordErrorCode = MutableStateFlow(Errors.DEFAULT)
	val passwordErrorCode: StateFlow<Errors> = _passwordErrorCode.asStateFlow()

	private val _repeat = MutableStateFlow("")
	val repeatPassword: StateFlow<String> = _repeat.asStateFlow()
	private val _repeatErrorCode = MutableStateFlow(Errors.DEFAULT)
	val repeatErrorCode: StateFlow<Errors> = _repeatErrorCode.asStateFlow()

	private val _waiting = MutableStateFlow(false)
	val busy: StateFlow<Boolean> = _waiting.asStateFlow()
	private val _errorMessage = MutableStateFlow("")
	val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

	fun setUsername(name: String) {
		_username.value = name
		_usernameErrorCode.value = Errors.DEFAULT
	}

	fun setPassword(password: String) {
		_password.value = password
		_passwordErrorCode.value = Errors.DEFAULT
		_repeatErrorCode.value = Errors.DEFAULT
	}

	fun setRepeatPassword(password: String) {
		_repeat.value = password
		_repeatErrorCode.value = Errors.DEFAULT
	}

	fun registration(callback: (message: String, code: Int) -> Unit) {
		_waiting.value = true

		val username = username.value.orEmpty()
		val password = password.value.orEmpty()
		val repeat = repeatPassword.value.orEmpty()

		if (username.length < 3) _usernameErrorCode.value = Errors.SHORT
		else if (username.length > 13) _usernameErrorCode.value = Errors.LONG

		if (password.length < 5) _passwordErrorCode.value = Errors.SHORT
		else if (password.length > 31) _passwordErrorCode.value = Errors.LONG

		if (password != repeat) _repeatErrorCode.value = Errors.NOT_EQUALS

		if (_usernameErrorCode.value == Errors.DEFAULT &&
			_passwordErrorCode.value == Errors.DEFAULT &&
			_repeatErrorCode.value == Errors.DEFAULT
		) {
			repository.registration(username, password) { message, code ->
				callback(message, code)
				_waiting.value = false
			}
		} else {
			_waiting.value = false
		}
	}
}
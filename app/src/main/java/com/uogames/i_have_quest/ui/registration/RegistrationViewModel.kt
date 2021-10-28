package com.uogames.i_have_quest.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uogames.model.GameProvider
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val repository: GameProvider) :
	ViewModel() {

	enum class Errors {
		DEFAULT, SHORT, LONG, EXIST, NOT_EQUALS
	}

	private val _username = MutableLiveData("")
	val username: LiveData<String> = _username
	private val _usernameErrorCode = MutableLiveData(Errors.DEFAULT)
	val usernameErrorCode: LiveData<Errors> = _usernameErrorCode

	private val _password = MutableLiveData("")
	val password: LiveData<String> = _password
	private val _passwordErrorCode = MutableLiveData(Errors.DEFAULT)
	val passwordErrorCode: LiveData<Errors> = _passwordErrorCode

	private val _repeat = MutableLiveData("")
	val repeatPassword: LiveData<String> = _repeat
	private val _repeatErrorCode = MutableLiveData(Errors.DEFAULT)
	val repeatErrorCode: LiveData<Errors> = _repeatErrorCode

	private val _waiting = MutableLiveData(false)
	val busy: LiveData<Boolean> = _waiting
	private val _errorMessage = MutableLiveData("")
	val errorMessage: LiveData<String> = _errorMessage

	fun setUsername(name: String) {
		_username.postValue(name)
		_usernameErrorCode.postValue(Errors.DEFAULT)
	}

	fun setPassword(password: String) {
		_password.postValue(password)
		_passwordErrorCode.postValue(Errors.DEFAULT)
		_repeatErrorCode.postValue(Errors.DEFAULT)
	}

	fun setRepeatPassword(password: String) {
		_repeat.postValue(password)
		_passwordErrorCode.postValue(Errors.DEFAULT)
		_repeatErrorCode.postValue(Errors.DEFAULT)
	}

	fun registration(callback: (message: String, code: Int) -> Unit) {
		_waiting.postValue(true)

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
				_waiting.postValue(false)
			}
		} else{
			_waiting.postValue(false)
		}
	}
}
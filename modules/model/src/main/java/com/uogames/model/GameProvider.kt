package com.uogames.model

import android.content.Context
import com.uogames.database.DatabaseRepository
import com.uogames.database.dto.UserDTO
import com.uogames.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameProvider private constructor(
	private val database: DatabaseRepository,
	private val networkRepository: Repository
) {

	private val ioScope = CoroutineScope(Dispatchers.IO)
	private val mainScope = CoroutineScope(Dispatchers.Main)

	companion object {
		private var INSTANCE: GameProvider? = null

		fun getINSTANCE(context: Context): GameProvider {
			if (INSTANCE == null) INSTANCE = GameProvider(
				database = DatabaseRepository.getINSTANCE(context),
				networkRepository = Repository()
			)
			return INSTANCE as GameProvider
		}
	}

	fun isLogin(callback: (Boolean) -> Unit) {
		ioScope.launch {
			val user = database.getMyUser()
			mainScope.launch { callback(user != null) }
		}
	}

	fun logIn(name: String, password: String, callback: (message: String, code: Int) -> Unit) {
		ioScope.launch {
			try {
				val user = networkRepository.login(name, password)
				if (user.status.type.value == 200) {
					mainScope.launch { callback(user.status.message, user.status.type.value) }
					database.saveUser(UserDTO(userKey = user.accessKey))
				} else {
					mainScope.launch { callback(user.status.message, user.status.type.value) }
				}
			} catch (e: Exception) {
				mainScope.launch { callback("Error Connect", 500) }
			}
		}
	}

	fun registration(
		name: String,
		password: String,
		callback: (message: String, code: Int) -> Unit
	) {
		ioScope.launch {
			try {
				val user = networkRepository.registration(name, password)
				if (user.status.type.value == 200) {
					mainScope.launch { callback(user.status.message, user.status.type.value) }
					database.saveUser(UserDTO(userKey = user.accessKey))
				} else {
					mainScope.launch { callback(user.status.message, user.status.type.value) }
				}
			} catch (e: Exception) {
				mainScope.launch { callback("Error Connect", 500) }
			}
		}
	}


	fun logOut(callback: () -> Unit) {
		ioScope.launch {
			database.deleteMyUser()
			mainScope.launch { callback() }
		}
	}
}
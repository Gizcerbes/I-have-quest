package com.uogames.model

import com.uogames.database.DatabaseRepository
import com.uogames.network.Repository
import com.uogames.network.data.dto.ResponseDTO
import com.uogames.network.data.dto.UserAccess
import kotlinx.coroutines.launch

internal class AccessProvider : Provider() {

	private fun updateData(
		access: ResponseDTO<UserAccess>,
		database: DatabaseRepository,
		callback: (message: String, code: Int) -> Unit
	) = ioScope.launch {
		when (access.status.type.value) {
			200 -> access.any?.apply { database.saveAccessKey(accessKey) }
			401 -> database.clear()
		}
		mainScope.launch { callback(access.status.message, access.status.type.value) }
	}

	fun isAccess(
		database: DatabaseRepository,
		callback: (Boolean) -> Unit
	) = ioScope.launch {
		database.getAccessKey().isNotEmpty().let { mainScope.launch { callback(it) } }
	}

	fun login(
		user: String,
		password: String,
		network: Repository,
		database: DatabaseRepository,
		callback: (message: String, code: Int) -> Unit
	) = ioScope.launch {
		val resp = network.login(user, password)
		updateData(resp, database, callback)
	}

	fun registration(
		user: String,
		password: String,
		network: Repository,
		database: DatabaseRepository,
		callback: (message: String, code: Int) -> Unit
	) = ioScope.launch {
		val resp = network.registration(user, password)
		updateData(resp, database, callback)
	}

}
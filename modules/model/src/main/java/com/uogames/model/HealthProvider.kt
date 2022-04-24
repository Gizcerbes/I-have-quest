package com.uogames.model

import com.uogames.database.DatabaseRepository
import com.uogames.dto.Health
import com.uogames.network.Repository
import com.uogames.network.data.dto.ResponseDTO
import kotlinx.coroutines.launch

class HealthProvider : Provider() {

	private fun update(
		response: ResponseDTO<Health>,
		database: DatabaseRepository,
		callback: (Health) -> Unit
	) = ioScope.launch {
		when (response.status.type.value) {
			200 -> response.any?.let {
				mainScope.launch { callback(it) }
				database.saveHealth(it)
			}
			401 -> database.clear()
		}
	}

	fun updateMyHealth(
		network: Repository,
		database: DatabaseRepository,
		callback: (Health) -> Unit = {}
	) = ioScope.launch {
		val resp = network.getMyHealth(database.getAccessKey())
		update(resp, database, callback)
	}

	fun updateHealthById(
		id: Long,
		network: Repository,
		database: DatabaseRepository,
		callback: (Health) -> Unit = {}
	) = ioScope.launch {
		val resp = network.getHealth(database.getAccessKey(), id)
		update(resp, database, callback)
	}

	fun getHealthById(
		id: Long,
		network: Repository,
		database: DatabaseRepository,
		callback: (Health) -> Unit
	) = ioScope.launch {
		database.getHealth(id)?.let { mainScope.launch { callback(it) } }
		updateHealthById(id, network, database, callback)
	}

	fun getMyHealth(
		network: Repository,
		database: DatabaseRepository,
		callback: (Health) -> Unit
	) = ioScope.launch {
		getHealthById(database.getUserId(), network, database, callback)
	}

}
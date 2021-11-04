package com.uogames.model

import com.uogames.database.DatabaseRepository
import com.uogames.network.Repository
import com.uogames.network.data.dto.Characteristic
import com.uogames.network.data.dto.ResponseDTO
import kotlinx.coroutines.launch

class CharacteristicProvider : Provider() {

	private fun update(
		response: ResponseDTO<Characteristic>,
		database: DatabaseRepository,
		callback: (Characteristic) -> Unit
	) = ioScope.launch {
		when (response.status.type.value) {
			200 -> response.any?.let {
				mainScope.launch { callback(it) }
				database.saveCharacteristics(it)
			}
			401 -> database.clear()
		}
	}

	fun updateMyCharacteristic(
		network: Repository,
		database: DatabaseRepository,
		callback: (Characteristic) -> Unit = {}
	) = ioScope.launch {
		val resp = network.getMyCharacteristic(database.getAccessKey())
		update(resp, database, callback)
	}

	fun updateCharacteristicById(
		id: Long,
		network: Repository,
		database: DatabaseRepository,
		callback: (Characteristic) -> Unit = {}
	) = ioScope.launch {
		val resp = network.getCharacteristic(database.getAccessKey(), id)
		update(resp, database, callback)
	}

	fun getCharacteristicById(
		id: Long,
		network: Repository,
		database: DatabaseRepository,
		callback: (Characteristic) -> Unit
	) = ioScope.launch {
		database.getCharacteristics(id)?.let { mainScope.launch { callback(it) } }
		updateCharacteristicById(id, network, database, callback)
	}

	fun getMyCharacteristic(
		network: Repository,
		database: DatabaseRepository,
		callback: (Characteristic) -> Unit
	) = ioScope.launch {
		getCharacteristicById(database.getUserId(), network, database, callback)
	}

}
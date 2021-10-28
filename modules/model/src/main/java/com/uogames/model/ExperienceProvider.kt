package com.uogames.model

import com.uogames.database.DatabaseRepository
import com.uogames.network.Repository
import com.uogames.network.data.dto.Experience
import com.uogames.network.data.dto.ResponseDTO
import kotlinx.coroutines.launch

class ExperienceProvider : Provider() {

	private fun update(
		response: ResponseDTO<Experience>,
		database: DatabaseRepository,
		callback: (Experience) -> Unit
	) = ioScope.launch {
		when (response.status.type.value) {
			200 -> response.any?.let {
				mainScope.launch { callback(it) }
				database.saveExperience(it)
			}
		}
	}

	fun updateMyExperience(
		network: Repository,
		database: DatabaseRepository,
		callback: (Experience) -> Unit
	) = ioScope.launch {
		val resp = network.getMyExperience(database.getAccessKey())
		update(resp, database, callback)
	}

	fun updateExperienceById(
		id: Long,
		network: Repository,
		database: DatabaseRepository,
		callback: (Experience) -> Unit
	) = ioScope.launch {
		val resp = network.getExperience(database.getAccessKey(), id)
		update(resp, database, callback)
	}

	fun getExperienceById(
		id: Long,
		network: Repository,
		database: DatabaseRepository,
		callback: (Experience) -> Unit
	) = ioScope.launch {
		database.getExperience(id)?.let { mainScope.launch { callback(it) } }
		updateExperienceById(id, network, database, callback)
	}

	fun getMyExperience(
		network: Repository,
		database: DatabaseRepository,
		callback: (Experience) -> Unit
	) = ioScope.launch {
		getExperienceById(database.getUserId(), network, database, callback)
	}

}
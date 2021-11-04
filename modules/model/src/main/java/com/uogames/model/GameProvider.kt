package com.uogames.model

import android.content.Context
import com.uogames.database.DatabaseRepository
import com.uogames.database.dto.Health
import com.uogames.network.Repository
import com.uogames.network.data.dto.Characteristic
import com.uogames.network.data.dto.Experience
import com.uogames.network.data.dto.Person
import kotlinx.coroutines.launch

class GameProvider private constructor(
	private val database: DatabaseRepository,
	private val networkRepository: Repository
) : Provider() {

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

	private val access = AccessProvider()
	private val characteristic = CharacteristicProvider()
	private val experience = ExperienceProvider()
	private val health = HealthProvider()
	private val person = PersonProvider()


	private fun updateAll() =
		ioScope.launch {
			person.updateMyPerson(networkRepository, database).join()
			characteristic.updateMyCharacteristic(networkRepository, database)
			experience.updateMyExperience(networkRepository, database)
			health.updateMyHealth(networkRepository, database)
		}


	fun isAccess(callback: (Boolean) -> Unit) = access.isAccess(database, callback)

	fun login(name: String, password: String, callback: (message: String, code: Int) -> Unit) =
		access.login(name, password, networkRepository, database) { mess, code ->
			callback(mess, code)
			updateAll()
		}

	fun registration(
		name: String,
		password: String,
		callback: (message: String, code: Int) -> Unit
	) =
		access.registration(name, password, networkRepository, database) { mess, code ->
			callback(mess, code)
			updateAll()
		}

	fun getMyPerson(callback: (Person) -> Unit) =
		person.getMyPerson(networkRepository, database, callback)

	fun getPersonById(id: Long, callback: (Person) -> Unit) =
		person.getPersonById(id, networkRepository, database, callback)

	fun getMyCharacteristic(callback: (Characteristic) -> Unit) =
		characteristic.getMyCharacteristic(networkRepository, database, callback)

	fun getCharacteristicById(id: Long, callback: (Characteristic) -> Unit) =
		characteristic.getCharacteristicById(id, networkRepository, database, callback)

	fun getMyExperience(callback: (Experience) -> Unit) =
		experience.getMyExperience(networkRepository, database, callback)

	fun getExperienceById(id: Long, callback: (Experience) -> Unit) =
		experience.getExperienceById(id, networkRepository, database, callback)

	fun getMyHealth(callback: (Health) -> Unit) =
		health.getMyHealth(networkRepository, database, callback)

	fun getHealthById(id: Long, callback: (Health) -> Unit) =
		health.getHealthById(id, networkRepository, database, callback)

}
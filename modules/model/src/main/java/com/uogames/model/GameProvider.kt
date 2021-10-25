package com.uogames.model

import android.content.Context
import com.uogames.database.DatabaseRepository
import com.uogames.network.Repository
import com.uogames.network.data.dto.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private val person = PersonProvider()

    private fun updateAll() {
        person.updateMyPerson(networkRepository, database) {}
    }

    fun isAccess(callback: (Boolean) -> Unit) = access.isAccess(database, callback)

    fun login(name: String, password: String, callback: (Boolean) -> Unit) =
        access.login(name, password, networkRepository, database, callback)

    fun registration(name: String, password: String, callback: (Boolean) -> Unit) =
        access.registration(name, password, networkRepository, database, callback)

    fun getMyPerson(callback: (Person) -> Unit) =
        person.getMyPerson(networkRepository, database, callback)

    fun getPersonById(id: Long, callback: (Person) -> Unit) =
        person.getPersonById(id, networkRepository, database, callback)

}
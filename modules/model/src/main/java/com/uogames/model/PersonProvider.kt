package com.uogames.model

import com.uogames.database.DatabaseRepository
import com.uogames.network.Repository
import com.uogames.network.data.dto.ObjectDTO
import com.uogames.network.data.dto.Person
import kotlinx.coroutines.launch

class PersonProvider : Provider() {

    private fun update(
        response: ObjectDTO<Person>,
        database: DatabaseRepository,
        callback: (Person) -> Unit
    ) = ioScope.launch {
        when (response.status.type.value) {
            200 -> response.any?.let {
                mainScope.launch { callback(it) }
                database.savePerson(it)
            }
            401 -> database.clear()
        }
    }

    fun updateMyPerson(
        network: Repository,
        database: DatabaseRepository,
        callback: (Person) -> Unit
    ) = ioScope.launch {
        val resp = network.getMyPerson(database.getAccessKey())
        update(resp, database) {
            ioScope.launch { database.saveUserId(it.id) }
            callback(it)
        }
    }

    fun updatePersonById(
        id: Long,
        network: Repository,
        database: DatabaseRepository,
        callback: (Person) -> Unit
    ) = ioScope.launch {
        val resp = network.getPerson(database.getAccessKey(), id)
        update(resp, database, callback)
    }

    fun getPersonById(
        id: Long,
        network: Repository,
        database: DatabaseRepository,
        callback: (Person) -> Unit
    ) = ioScope.launch {
        database.getPerson(id)?.let { mainScope.launch { callback(it) } }
        updatePersonById(id, network, database, callback)
    }

    fun getMyPerson(
        network: Repository,
        database: DatabaseRepository,
        callback: (Person) -> Unit
    ) = ioScope.launch {
        getPersonById(database.getUserId(), network, database, callback)
    }


}
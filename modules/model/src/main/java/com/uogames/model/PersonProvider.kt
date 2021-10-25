package com.uogames.model

import com.uogames.database.DatabaseRepository
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


}
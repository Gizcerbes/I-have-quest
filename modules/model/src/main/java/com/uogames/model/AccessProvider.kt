package com.uogames.model

import com.uogames.database.DatabaseRepository
import com.uogames.network.Repository
import com.uogames.network.data.dto.ObjectDTO
import com.uogames.network.data.dto.UserAccess
import kotlinx.coroutines.launch

internal class AccessProvider : Provider() {

    private fun updateData(
        access: ObjectDTO<UserAccess>,
        database: DatabaseRepository,
        callback: (Boolean) -> Unit
    ) = ioScope.launch {
        when (access.status.type.value) {
            200 -> access.any?.apply {
                mainScope.launch { callback(true) }
                database.saveAccessKey(accessKey)
            }
            else -> {
                database.clear()
                mainScope.launch { callback(false) }
            }
        }

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
        callback: (Boolean) -> Unit
    ) = ioScope.launch {
        val resp = network.login(user, password)
        updateData(resp, database, callback)
    }

    fun registration(
        user: String,
        password: String,
        network: Repository,
        database: DatabaseRepository,
        callback: (Boolean) -> Unit
    ) = ioScope.launch {
        val resp = network.registration(user, password)
        updateData(resp, database, callback)
    }

}
package com.uogames.network.data.mappers

import com.uogames.database.dto.Health
import com.uogames.network.data.entities.HealthResponse

object HealthMapper : Mapper<HealthResponse, Health> {
    override fun HealthResponse.toDTO(): Health {
        return Health(
            id = id ?: 0,
            health = health ?: 0,
            timeUpdate = timeUpdate ?: 0
        )
    }
}
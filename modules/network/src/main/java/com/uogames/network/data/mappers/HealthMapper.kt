package com.uogames.network.data.mappers

import com.uogames.network.data.dto.Health
import com.uogames.network.data.entities.HealthResponse

object HealthMapper : Mapper<HealthResponse, Health> {
    override fun HealthResponse.toDTO(): Health {
        return Health(
            id = id,
            health = health,
            timeUpdate = timeUpdate
        )
    }
}
package com.uogames.database.mapper

import com.uogames.database.dto.Health
import com.uogames.database.entity.HealthEntity

object HealthMapper : Mapper<HealthEntity, Health> {
    override fun HealthEntity.toDTO(): Health {
        return Health(
            id = id,
            health = health,
            timeUpdate = timeUpdate
        )
    }

    override fun Health.toEntity(): HealthEntity {
        return HealthEntity(
            id = id,
            health = health,
            timeUpdate = timeUpdate
        )
    }
}
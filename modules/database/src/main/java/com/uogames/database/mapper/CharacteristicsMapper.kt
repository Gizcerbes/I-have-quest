package com.uogames.database.mapper

import com.uogames.database.entity.CharacteristicEntity
import com.uogames.network.data.dto.Characteristic

object CharacteristicsMapper : Mapper<CharacteristicEntity, Characteristic> {
    override fun CharacteristicEntity.toDTO(): Characteristic {
        return Characteristic(
            id = id,
            agility = agility,
            defence = defence,
            force = force,
            vitality = vitality
        )
    }

    override fun Characteristic.toEntity(): CharacteristicEntity {
        return CharacteristicEntity(
            id = id,
            agility = agility,
            defence = defence,
            force = force,
            vitality = vitality
        )
    }
}
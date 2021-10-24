package com.uogames.database.mapper

import com.uogames.database.dto.Characteristic
import com.uogames.database.entity.CharacteristicEntity

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
package com.uogames.network.data.mappers

import com.uogames.network.data.dto.Characteristic
import com.uogames.network.data.entities.CharacteristicResponse

object CharacteristicMapper : Mapper<CharacteristicResponse, Characteristic> {
    override fun CharacteristicResponse.toDTO(): Characteristic {
        return Characteristic(
            agility = agility ?: 0,
            defence = defence ?: 0,
            force = force ?: 0,
            id = id ?: 0,
            vitality = vitality ?: 0
        )
    }
}
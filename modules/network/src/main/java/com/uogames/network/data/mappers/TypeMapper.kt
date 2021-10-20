package com.uogames.network.data.mappers

import com.uogames.network.data.dto.TypeDTO
import com.uogames.network.data.entities.TypeResponse

public object TypeMapper : Mapper<TypeResponse, TypeDTO> {

	override fun TypeResponse.toDTO(): TypeDTO {
		return TypeDTO(
			description = description.orEmpty(),
			value = value ?: 0
		)
	}

}
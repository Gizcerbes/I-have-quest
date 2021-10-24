package com.uogames.network.data.mappers

import com.uogames.network.data.dto.Type
import com.uogames.network.data.entities.TypeResponse

object TypeMapper : Mapper<TypeResponse, Type> {

	override fun TypeResponse.toDTO(): Type {
		return Type(
			description = description.orEmpty(),
			value = value ?: 0
		)
	}

}
package com.uogames.network.data.mappers

import com.uogames.network.data.dto.StatusDTO
import com.uogames.network.data.entities.StatusResponse
import com.uogames.network.data.mappers.TypeMapper.toDTO

public object StatusMapper : Mapper<StatusResponse, StatusDTO> {
	override fun StatusResponse.toDTO(): StatusDTO {
		return StatusDTO(
			message = message.orEmpty(),
			timestamp = timestamp ?: 0,
			type = type.toDTO()
		)
	}
}
package com.uogames.network.data.mappers

import com.uogames.network.data.dto.Status
import com.uogames.network.data.entities.StatusResponse
import com.uogames.network.data.mappers.TypeMapper.toDTO

object StatusMapper : Mapper<StatusResponse, Status> {
	override fun StatusResponse.toDTO(): Status {
		return Status(
			message = message.orEmpty(),
			timestamp = timestamp ?: 0,
			type = type.toDTO()
		)
	}
}
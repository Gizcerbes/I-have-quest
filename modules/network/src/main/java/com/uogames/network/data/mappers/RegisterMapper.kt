package com.uogames.network.data.mappers

import com.uogames.network.data.dto.UserAccessDTO
import com.uogames.network.data.entities.RegisterResponse
import com.uogames.network.data.mappers.StatusMapper.toDTO

public object RegisterMapper : Mapper<RegisterResponse, UserAccessDTO> {
	override fun RegisterResponse.toDTO(): UserAccessDTO {
		return UserAccessDTO(
			accessKey = accessKey.orEmpty(),
			status = status.toDTO()
		)
	}
}
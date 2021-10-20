package com.uogames.network.data.mappers

import com.uogames.network.data.dto.UserAccessDTO
import com.uogames.network.data.entities.LoginResponse
import com.uogames.network.data.mappers.StatusMapper.toDTO


public object LoginMapper: Mapper<LoginResponse, UserAccessDTO> {
	override fun LoginResponse.toDTO(): UserAccessDTO {
		return UserAccessDTO(
			accessKey = accessKey.orEmpty(),
			status = status.toDTO()
		)
	}
}
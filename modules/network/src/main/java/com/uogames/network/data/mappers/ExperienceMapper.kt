package com.uogames.network.data.mappers

import com.uogames.dto.Experience
import com.uogames.network.data.entities.ExperienceResponse

object ExperienceMapper : Mapper<ExperienceResponse, Experience> {
    override fun ExperienceResponse.toDTO(): Experience {
        return Experience(
            id = id ?: 0,
            lvl = lvl ?: 0,
            experience = experience ?: 0
        )
    }
}
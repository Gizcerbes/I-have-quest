package com.uogames.database.mapper

import com.uogames.database.dto.Experience
import com.uogames.database.entity.ExperienceEntity

object ExperienceMapper : Mapper<ExperienceEntity, Experience> {
    override fun ExperienceEntity.toDTO() = Experience(
        id = id,
        experience = experience,
        lvl = lvl
    )


    override fun Experience.toEntity() = ExperienceEntity(
        id = id,
        experience = experience,
        lvl = lvl
    )
}
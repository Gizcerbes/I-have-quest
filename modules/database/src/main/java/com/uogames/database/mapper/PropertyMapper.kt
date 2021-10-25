package com.uogames.database.mapper

import com.uogames.database.dto.Property
import com.uogames.database.entity.PropertyEntity

object PropertyMapper : Mapper<PropertyEntity, Property> {
    override fun PropertyEntity.toDTO(): Property {
        return Property(id, value)
    }

    override fun Property.toEntity(): PropertyEntity {
        return PropertyEntity(id, value)
    }
}
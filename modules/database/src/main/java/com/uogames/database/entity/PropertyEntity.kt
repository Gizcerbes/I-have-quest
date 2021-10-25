package com.uogames.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_property")
data class PropertyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val value: String
)
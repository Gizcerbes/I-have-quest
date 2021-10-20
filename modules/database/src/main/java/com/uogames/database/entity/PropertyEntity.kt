package com.uogames.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_property")
data class MyPersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val key: String,
    val value: String
)
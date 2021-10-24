package com.uogames.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characteristics")
data class CharacteristicEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val agility: Int,
    val defence: Int,
    val force: Int,
    val vitality: Int
)
package com.uogames.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health")
data class HealthEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val health: Long,
    val timeUpdate: Long
)

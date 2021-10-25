package com.uogames.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experience")
data class ExperienceEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val experience: Long,
    val lvl: Long
)

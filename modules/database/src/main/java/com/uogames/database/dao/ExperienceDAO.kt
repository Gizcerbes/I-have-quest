package com.uogames.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.uogames.database.entity.ExperienceEntity

@Dao
interface ExperienceDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(experienceEntity: ExperienceEntity)

    @Delete
    suspend fun delete(experienceEntity: ExperienceEntity)

    @Query("DELETE FROM experience")
    suspend fun clear()

    @Query("SELECT * FROM experience WHERE `id` = :id")
    suspend fun getExperienceById(id: Long): ExperienceEntity?

}
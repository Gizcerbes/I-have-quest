package com.uogames.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.uogames.database.entity.HealthEntity

@Dao
interface HealthDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(healthEntity: HealthEntity)

    @Delete
    suspend fun delete(healthEntity: HealthEntity)

    @Query("DELETE FROM health")
    suspend fun clear()

    @Query("SELECT * FROM health WHERE `id`=:id")
    suspend fun getHealthById(id: Long): HealthEntity?
}
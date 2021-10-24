package com.uogames.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.uogames.database.entity.CharacteristicEntity

@Dao
interface CharacteristicsDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(characteristicEntity: CharacteristicEntity)

    @Delete
    suspend fun delete(characteristicEntity: CharacteristicEntity)

    @Query("DELETE FROM characteristics")
    suspend fun clear()

    @Query("SELECT * FROM characteristics WHERE `id`=:id")
    suspend fun getCharacteristicsById(id: Long): CharacteristicEntity?

}
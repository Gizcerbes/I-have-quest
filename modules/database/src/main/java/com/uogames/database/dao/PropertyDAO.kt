package com.uogames.database.dao

import androidx.room.*
import com.uogames.database.entity.PropertyEntity

@Dao
interface PropertyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myPerson: PropertyEntity)

    @Delete
    suspend fun delete(myPerson: PropertyEntity)

    @Query("DELETE FROM game_property WHERE `id`=:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM game_property WHERE `id`=:id")
    suspend fun searchPropertyById(id: Int): PropertyEntity?

    @Query("DELETE FROM game_property")
    suspend fun clear()

}
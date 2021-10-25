package com.uogames.database.dao

import androidx.room.*
import com.uogames.database.entity.PersonEntity

@Dao
interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: PersonEntity)

    @Delete
    suspend fun delete(person: PersonEntity)

    @Query("DELETE FROM persons")
    suspend fun clear()

    @Query("SELECT * FROM persons WHERE `id`=:id")
    suspend fun getPersonById(id: Long): PersonEntity?

    @Query("SELECT * FROM persons WHERE `name`=:name")
    suspend fun getPersonByName(name: String): PersonEntity?

}
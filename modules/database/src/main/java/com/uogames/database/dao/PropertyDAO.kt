package com.uogames.database.dao

import androidx.room.*
import com.uogames.database.entity.MyPersonEntity

@Dao
interface MyPersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myPerson: MyPersonEntity)

    @Delete
    suspend fun delete(myPerson: MyPersonEntity)

    @Query("SELECT * FROM ")
    suspend fun getPerson() : MyPersonEntity

}
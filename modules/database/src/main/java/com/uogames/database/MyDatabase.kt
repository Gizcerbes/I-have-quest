package com.uogames.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uogames.database.dao.*
import com.uogames.database.entity.*

@Database(
    entities = [
        PropertyEntity::class,
        PersonEntity::class,
        CharacteristicEntity::class,
        HealthEntity::class,
        ExperienceEntity::class
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getINSTANCE(context: Context): MyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MyDatabase::class.java, "database").build()
            }
            return INSTANCE as MyDatabase
        }

    }

    abstract fun propertyDAO(): PropertyDAO

    abstract fun personDAO(): PersonDAO

    abstract fun characteristicsDAO(): CharacteristicsDAO

    abstract fun healthDAO(): HealthDAO

    abstract fun experienceDAO(): ExperienceDAO
}
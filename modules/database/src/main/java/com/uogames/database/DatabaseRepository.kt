package com.uogames.database

import android.content.Context
import com.uogames.database.dto.Characteristic
import com.uogames.database.dto.Experience
import com.uogames.database.dto.Health
import com.uogames.database.dto.Person
import com.uogames.database.entity.PropertyEntity
import com.uogames.database.mapper.CharacteristicsMapper.toDTO
import com.uogames.database.mapper.CharacteristicsMapper.toEntity
import com.uogames.database.mapper.ExperienceMapper.toDTO
import com.uogames.database.mapper.ExperienceMapper.toEntity
import com.uogames.database.mapper.HealthMapper.toDTO
import com.uogames.database.mapper.HealthMapper.toEntity
import com.uogames.database.mapper.PersonMapper.toDTO
import com.uogames.database.mapper.PersonMapper.toEntity

class DatabaseRepository private constructor(private val database: MyDatabase) {

    companion object {
        private var INSTANCE: DatabaseRepository? = null

        fun getINSTANCE(context: Context): DatabaseRepository {
            if (INSTANCE == null) {
                INSTANCE = DatabaseRepository(MyDatabase.getINSTANCE(context))
            }
            return INSTANCE as DatabaseRepository
        }
    }

    enum class Const(val id: Int) {
        ACCESS_KEY(1), USER_ID(2)
    }

    private suspend fun saveProperty(property: String, key: Const) =
        database.propertyDAO().insert(PropertyEntity(key.id, property))

    private suspend fun getProperty(key: Const) =
        database.propertyDAO().searchPropertyById(key.id)?.value

    private suspend fun removeProperty(key: Const) = database.propertyDAO().delete(key.id)

    suspend fun clear() {
        database.experienceDAO().clear()
        database.healthDAO().clear()
        database.characteristicsDAO().clear()
        database.personDAO().clear()
        database.propertyDAO().clear()
    }

    suspend fun saveAccessKey(access_key: String) = saveProperty(access_key, Const.ACCESS_KEY)

    suspend fun getAccessKey() = getProperty(Const.ACCESS_KEY).orEmpty()

    suspend fun removeAccessKey() = removeProperty(Const.ACCESS_KEY)

    suspend fun saveUserId(id: Long) = saveProperty(id.toString(), Const.USER_ID)

    suspend fun getUserId() = getProperty(Const.USER_ID).let { it?.toLong() ?: 0 }

    suspend fun savePerson(person: Person) = database.personDAO().insert(person.toEntity())

    suspend fun getPerson(id: Long) = database.personDAO().getPersonById(id)?.toDTO()

    suspend fun saveCharacteristics(characteristics: Characteristic) =
        database.characteristicsDAO().insert(characteristics.toEntity())

    suspend fun getCharacteristics(id: Long) =
        database.characteristicsDAO().getCharacteristicsById(id)?.toDTO()

    suspend fun saveHealth(health: Health) = database.healthDAO().insert(health.toEntity())

    suspend fun getHealth(id: Long) = database.healthDAO().getHealthById(id)?.toDTO()

    suspend fun saveExperience(experience: Experience) =
        database.experienceDAO().insert(experience.toEntity())

    suspend fun getExperience(id: Long) = database.experienceDAO().getExperienceById(id)?.toDTO()
}
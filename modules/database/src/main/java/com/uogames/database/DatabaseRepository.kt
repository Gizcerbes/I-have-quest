package com.uogames.database

import android.content.Context
import com.uogames.database.dto.UserDTO
import com.uogames.database.mapper.UserMapper.toDTO
import com.uogames.database.mapper.UserMapper.toEntity

class DatabaseRepository private constructor(private val database: MyDatabase){

	companion object{
		private var INSTANCE : DatabaseRepository? = null

		fun getINSTANCE(context: Context): DatabaseRepository {
			if(INSTANCE == null) {
				INSTANCE = DatabaseRepository(MyDatabase.getINSTANCE(context))
			}
			return INSTANCE as DatabaseRepository
		}
	}

	suspend fun saveUser(user: UserDTO){
		database.userDAO().getUser()?.let { database.userDAO().delete(it) }
		database.userDAO().insert(user.toEntity())
	}

	suspend fun getMyUser(): UserDTO? {
		return database.userDAO().getUser()?.toDTO()
	}

	suspend fun deleteMyUser(){
		database.userDAO().getUser()?.let { database.userDAO().delete(it) }
	}


}
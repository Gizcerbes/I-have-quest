package com.uogames.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uogames.database.dao.UserDAO
import com.uogames.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

	companion object{
		private var INSTANCE: MyDatabase? = null

		fun getINSTANCE(context: Context): MyDatabase {
			if (INSTANCE == null){
				INSTANCE = Room.databaseBuilder(context, MyDatabase::class.java, "database").build()
			}
			return  INSTANCE as MyDatabase
		}

	}

	abstract fun userDAO() : UserDAO



}
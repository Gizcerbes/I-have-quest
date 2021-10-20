package com.uogames.database.mapper

interface Mapper<O,T> {

	fun O.toDTO():T

	fun T.toEntity(): O

}
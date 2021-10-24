package com.uogames.network.data.mappers

interface Mapper<S, N> {

	fun S.toDTO(): N

}